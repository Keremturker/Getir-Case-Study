package com.kturker.feature.product.presentation.list

import androidx.lifecycle.viewModelScope
import com.kturker.core.CoreViewModel
import com.kturker.core.onError
import com.kturker.core.onSuccess
import com.kturker.feature.product.domain.usecase.ProductsUseCase
import com.kturker.feature.product.domain.usecase.SuggestedProductUseCase
import com.kturker.language.ML
import com.kturker.language.StringResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ProductListViewmodel @Inject constructor(
    private val productsUseCase: ProductsUseCase,
    private val suggestedProductUseCase: SuggestedProductUseCase,
    stringResourceManager: StringResourceManager
) : CoreViewModel(), ProductListUiActions {

    private val _uiState = MutableStateFlow(
        ProductListUiState(
            title = stringResourceManager[ML::productListTitle]
        )
    )
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {

        safeFlowApiCall {
            productsUseCase()
        }.onSuccess { response ->
            _uiState.update { state ->
                state.copy(productList = response)
            }
        }.onError {
            val errorMessage = it
        }.launchIn(viewModelScope)

        safeFlowApiCall {
            suggestedProductUseCase()
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

    override fun onMinusAction(id: String) {
        _uiState.update { currentState ->
            val updatedProducts = currentState.suggestedProductList.map { product ->
                if (product.id == id && product.cartCount > 0) {
                    product.copy(cartCount = product.cartCount - 1)
                } else {
                    product
                }
            }

            currentState.copy(suggestedProductList = updatedProducts)
        }
    }

    override fun onPlusAction(id: String) {
        _uiState.update { currentState ->
            val updatedProducts = currentState.suggestedProductList.map { product ->
                if (product.id == id) {
                    product.copy(cartCount = product.cartCount + 1)
                } else {
                    product
                }
            }

            currentState.copy(suggestedProductList = updatedProducts)
        }
    }

}