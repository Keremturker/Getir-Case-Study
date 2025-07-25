package com.kturker.feature.product.presentation.list

import com.kturker.feature.product.domain.model.ProductItem
import com.kturker.feature.product.domain.model.SuggestedProductItem

internal data class ProductListUiState(
    val title: String = "",
    val productList: List<ProductItem> = listOf(),
    val suggestedProductList: List<SuggestedProductItem> = listOf()
)
