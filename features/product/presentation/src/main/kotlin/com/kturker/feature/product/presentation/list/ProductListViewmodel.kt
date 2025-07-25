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
) : CoreViewModel(), ProductListActionHandler {

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

/*    fun onMinusActionForSuggestProduct(id: String) {
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

    fun onPlusActionForSuggestProduct(id: String) {
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

    fun onMinusActionForProduct(id: String) {
        _uiState.update { currentState ->
            val updatedProducts = currentState.productList.map { product ->
                if (product.id == id && product.cartCount > 0) {
                    product.copy(cartCount = product.cartCount - 1)
                } else {
                    product
                }
            }

            currentState.copy(productList = updatedProducts)
        }
    }

    fun onPlusActionForProduct(id: String) {
        _uiState.update { currentState ->
            val updatedProducts = currentState.productList.map { product ->
                if (product.id == id) {
                    product.copy(cartCount = product.cartCount + 1)
                } else {
                    product
                }
            }

            currentState.copy(productList = updatedProducts)
        }
    }*/

    override fun onAction(action: ProductListAction) {
        when (action) {
            is ProductListAction.Add -> {
                updateProductList(action.source, action.id) { it + 1 }
            }

            is ProductListAction.Remove -> {
                updateProductList(action.source, action.id) { count -> if (count > 0) count - 1 else 0 }
            }
        }
    }

    private fun updateProductList(
        source: ProductListAction.Source,
        id: String,
        updateCount: (Int) -> Int
    ) {
        _uiState.update { currentState ->
            when (source) {
                ProductListAction.Source.Suggested -> {
                    val updated = currentState.suggestedProductList.map { product ->
                        if (product.id == id) product.copy(cartCount = updateCount(product.cartCount))
                        else product
                    }
                    currentState.copy(suggestedProductList = updated)
                }

                ProductListAction.Source.All -> {
                    val updated = currentState.productList.map { product ->
                        if (product.id == id) product.copy(cartCount = updateCount(product.cartCount))
                        else product
                    }
                    currentState.copy(productList = updated)
                }
            }
        }
    }

}