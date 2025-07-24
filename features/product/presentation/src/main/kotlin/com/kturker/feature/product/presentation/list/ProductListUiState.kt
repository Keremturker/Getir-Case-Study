package com.kturker.feature.product.presentation.list

import com.kturker.feature.product.domain.model.Product

internal data class ProductListUiState(
    val title: String = "",
    val productList: List<Product> = listOf()
)
