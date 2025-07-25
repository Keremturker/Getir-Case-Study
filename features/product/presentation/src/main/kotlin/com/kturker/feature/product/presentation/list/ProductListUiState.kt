package com.kturker.feature.product.presentation.list

import com.kturker.feature.product.domain.model.ProductItem

internal data class ProductListUiState(
    val title: String = "",
    val productList: List<ProductItem> = listOf(),
    val suggestedProductList: List<ProductItem> = listOf()
)
