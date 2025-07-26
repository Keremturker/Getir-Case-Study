package com.kturker.feature.product.presentation.list

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.kturker.contract.ResultState
import com.kturker.core.CoreViewModel
import com.kturker.core.domain.ProductItem
import com.kturker.feature.product.domain.usecase.AddToCartUseCase
import com.kturker.feature.product.domain.usecase.GetCartTotalPriceUseCase
import com.kturker.feature.product.domain.usecase.ProductsUseCase
import com.kturker.feature.product.domain.usecase.RemoveFromCartUseCase
import com.kturker.feature.product.domain.usecase.SuggestedProductsUseCase
import com.kturker.language.ML
import com.kturker.language.StringResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
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
            title = stringResourceManager[ML::productListTitle],
            goToCartButtonTitle = stringResourceManager[ML::goToCartButtonTitle],
            emptyListText = stringResourceManager[ML::emptyListText]
        )
    )
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage: SharedFlow<String> = _snackbarMessage.asSharedFlow()

    @SuppressLint("DefaultLocale")
    private fun observeCartTotalPrice() {
        getCartTotalPrice()
            .distinctUntilChanged()
            .onEach { totalPrice ->
                val formattedPrice =
                    String.format("₺%.2f", totalPrice).takeIf { totalPrice > 0.0 }.orEmpty()
                _uiState.update { state ->
                    state.copy(totalPriceFormatted = formattedPrice)
                }
            }
            .launchIn(viewModelScope)
    }

    init {
        observeCartTotalPrice()
        onFetchData(defaultOnLoading = false)
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

    override fun onFetchData(defaultOnLoading: Boolean) {
        _uiState.update { it.copy(isRefreshing = defaultOnLoading, isLoading = true) }

        viewModelScope.launch {
            refreshProducts().collect { result ->
                when (result) {
                    is RefreshResult.PartialSuccess -> {
                        _uiState.update {
                            it.copy(
                                productList = result.products.orEmpty(),
                                suggestedProductList = result.suggestedProducts.orEmpty(),
                                isRefreshing = false,
                                isLoading = false
                            )
                        }

                        result.errorMessage?.let { errorMessage ->
                            sendSnackbar(errorMessage)
                        }
                    }

                    is RefreshResult.Error -> {
                        _uiState.update { it.copy(isRefreshing = false, isLoading = false) }

                        sendSnackbar(result.message)
                    }
                }
            }
        }
    }

    private fun refreshProducts(): Flow<RefreshResult> {
        return combine(
            flow = fetchProducts(),
            flow2 = fetchSuggestedProducts()
        ) { allProductsResult, suggestedResult ->

            val products = (allProductsResult as? ResultState.Success)?.data
            val suggested = (suggestedResult as? ResultState.Success)?.data

            val allProductsError = (allProductsResult as? ResultState.Error)?.message
            val suggestedError = (suggestedResult as? ResultState.Error)?.message

            return@combine when {
                products != null || suggested != null -> {
                    val combinedErrorMessage =
                        listOfNotNull(allProductsError, suggestedError).joinToString(" & ")
                    RefreshResult.PartialSuccess(
                        products = products,
                        suggestedProducts = suggested,
                        errorMessage = combinedErrorMessage.ifBlank { null }
                    )
                }

                else -> {
                    RefreshResult.Error(
                        message = allProductsError ?: suggestedError ?: "Unknown error"
                    )
                }
            }
        }
    }

    fun sendSnackbar(message: String) {
        viewModelScope.launch {
            _snackbarMessage.emit(message)
        }
    }

}