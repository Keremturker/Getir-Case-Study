package com.kturker.feature.product.presentation.list

import com.kturker.core.domain.ProductItem

internal data class ProductListUiState(
    val title: String = "",
    val totalPriceFormatted: String = "",
    val productList: List<ProductItem> = listOf(),
    val suggestedProductList: List<ProductItem> = listOf()
)
