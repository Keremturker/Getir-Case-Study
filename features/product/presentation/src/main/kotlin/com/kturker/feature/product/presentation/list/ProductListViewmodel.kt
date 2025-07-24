package com.kturker.feature.product.presentation.list

import androidx.lifecycle.viewModelScope
import com.kturker.core.CoreViewModel
import com.kturker.feature.product.domain.usecase.GetCombinedProductUseCase
import com.kturker.language.ML
import com.kturker.language.StringResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductListViewmodel @Inject constructor(
    private val getAllProducts: GetCombinedProductUseCase,
    private val stringResourceManager: StringResourceManager
) : CoreViewModel() {

    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {

            getAllProducts.invoke()

            _uiState.update {
                it.copy(
                    title = stringResourceManager[ML::productListTitle]
                )
            }
        }
    }
}