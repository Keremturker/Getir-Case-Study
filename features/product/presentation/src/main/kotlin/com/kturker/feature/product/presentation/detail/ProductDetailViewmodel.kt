package com.kturker.feature.product.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.kturker.core.presentation.CoreViewModel
import com.kturker.core.presentation.getTypedArg
import com.kturker.feature.product.contract.ProductDetailArgs
import com.kturker.feature.product.presentation.navigation.ProductNavigation
import com.kturker.language.ML
import com.kturker.language.StringResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailViewmodel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigation: ProductNavigation,
    stringResourceManager: StringResourceManager
) : CoreViewModel(), ProductDetailAction {

    val args: ProductDetailArgs by lazy { savedStateHandle.getTypedArg<ProductDetailArgs>() }

    private val _uiState = MutableStateFlow(
        value = ProductDetailUiState(
            title = stringResourceManager[ML::productDetailTitle]
        )
    )
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                imageUrl = args.imageUrl,
                description = args.description,
                priceText = args.priceText,
                name = args.name
            )
        }
    }

    override fun navigateUp() {
        navigation.navigateUp()
    }

}