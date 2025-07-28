package com.kturker.feature.product.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kturker.core.domain.model.ProductItem
import com.kturker.core.domain.usecase.AddToCartUseCase
import com.kturker.core.domain.usecase.GetCartTotalPriceUseCase
import com.kturker.core.domain.usecase.RemoveFromCartUseCase
import com.kturker.core.presentation.CoreViewModel
import com.kturker.feature.product.domain.usecase.ProductPagingUseCase
import com.kturker.feature.product.domain.usecase.RefreshProductsUseCase
import com.kturker.feature.product.domain.usecase.SuggestedProductPagingUseCase
import com.kturker.feature.product.presentation.navigation.ProductNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductListViewmodel @Inject constructor(
    private val addToCart: AddToCartUseCase,
    private val removeFromCart: RemoveFromCartUseCase,
    private val navigation: ProductNavigation,
    private val refreshProducts: RefreshProductsUseCase,
    productPaging: ProductPagingUseCase,
    suggestedProductPaging: SuggestedProductPagingUseCase,
    getCartTotalPrice: GetCartTotalPriceUseCase
) : CoreViewModel(), ProductListAction {

    private val _uiState = MutableStateFlow(value = ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage: SharedFlow<String> = _snackbarMessage.asSharedFlow()

    val productList = productPaging().cachedIn(viewModelScope)

    val suggestedProductList = suggestedProductPaging().cachedIn(viewModelScope)

    val totalPriceFormatted = getCartTotalPrice()

    private var fetchedBefore: Boolean = false

    override fun navigateToDetailScreen(item: ProductItem) {
        navigation.navigateToDetailScreen(item = item)
    }

    override fun navigateToCartScreen() {
        navigation.navigateToCartScreen()
    }

    override fun addToCart(item: ProductItem) {
        viewModelScope.launch {
            addToCart.invoke(item)
        }
    }

    override fun removeFromCart(item: ProductItem) {
        viewModelScope.launch {
            removeFromCart.invoke(id = item.id)
        }
    }

    override fun onFetchData(defaultIsRefreshing: Boolean) {
        if (fetchedBefore && defaultIsRefreshing.not()) return

        fetchedBefore = true

        _uiState.update { it.copy(isRefreshing = defaultIsRefreshing, isLoading = true) }

        viewModelScope.launch {
            refreshProducts().collect { result ->
                _uiState.update { it.copy(isRefreshing = false, isLoading = false) }

                result?.let {
                    sendSnackbar(result)
                }
            }
        }
    }

    private fun sendSnackbar(message: String) {
        viewModelScope.launch {
            _snackbarMessage.emit(message)
        }
    }

    fun updateTotalPrice(totalPrice: String) {
        _uiState.update { state ->
            state.copy(totalPriceFormatted = totalPrice)
        }
    }
}
