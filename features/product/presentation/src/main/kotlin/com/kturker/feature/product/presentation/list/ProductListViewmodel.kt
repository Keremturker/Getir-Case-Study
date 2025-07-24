package com.kturker.feature.product.presentation.list

import androidx.lifecycle.viewModelScope
import com.kturker.core.CoreViewModel
import com.kturker.core.onSuccess
import com.kturker.feature.product.domain.usecase.AllProductsUseCase
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
    private val getAllProducts: AllProductsUseCase,
    private val stringResourceManager: StringResourceManager
) : CoreViewModel() {

    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {

        safeFlowApiCall {
            getAllProducts()
        }.onSuccess {
            _uiState.update {state->
                state.copy(title = stringResourceManager[ML::productListTitle])
            }
        }.launchIn(viewModelScope)
    }

}