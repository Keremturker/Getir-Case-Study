package com.kturker.feature.product.presentation.list

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.kturker.core.CoreViewModel
import com.kturker.core.domain.ProductItem
import com.kturker.core.onError
import com.kturker.core.onSuccess
import com.kturker.feature.product.domain.usecase.AddToCartUseCase
import com.kturker.feature.product.domain.usecase.GetCartTotalPriceUseCase
import com.kturker.feature.product.domain.usecase.ProductsUseCase
import com.kturker.feature.product.domain.usecase.RemoveFromCartUseCase
import com.kturker.feature.product.domain.usecase.SuggestedProductsUseCase
import com.kturker.language.ML
import com.kturker.language.StringResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductListViewmodel @Inject constructor(
    private val fetchProducts: ProductsUseCase,
    private val fetchSuggestedProducts: SuggestedProductsUseCase,
    private val addToCart: AddToCartUseCase,
    private val removeFromCart: RemoveFromCartUseCase,
    private val getCartTotalPrice: GetCartTotalPriceUseCase,
    stringResourceManager: StringResourceManager
) : CoreViewModel(), ProductListAction {

    private val _uiState = MutableStateFlow(
        ProductListUiState(
            title = stringResourceManager[ML::productListTitle]
        )
    )
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    @SuppressLint("DefaultLocale")
    private fun observeCartTotalPrice() {
        getCartTotalPrice()
            .map { total -> String.format("₺%.2f", total) }
            .distinctUntilChanged()
            .onEach { formattedPrice ->
                _uiState.update { state ->
                    state.copy(totalPriceFormatted = formattedPrice)
                }
            }
            .launchIn(viewModelScope)
    }

    init {
        observeCartTotalPrice()

        safeFlowApiCall {
            fetchProducts()
        }.onSuccess { response ->
            _uiState.update { state ->
                state.copy(productList = response)
            }
        }.onError {
            val errorMessage = it
        }.launchIn(viewModelScope)

        safeFlowApiCall {
            fetchSuggestedProducts()
        }.onSuccess { response ->
            _uiState.update { state ->
                state.copy(
                    suggestedProductList = response
                )
            }
        }.onError {
            val errorMessage = it
        }.launchIn(viewModelScope)
    }

    override fun addToCart(item: ProductItem) {
        viewModelScope.launch {
            addToCart.invoke(item)
        }
    }

    override fun removeFromCart(item: ProductItem) {
        viewModelScope.launch {
            removeFromCart.invoke(item = item)
        }
    }

}