package com.kturker.feature.product.presentation.detail

internal data class ProductDetailUiState(
    val title: String = "",
    val imageUrl: String = "",
    val name: String = "",
    val description: String = "",
    val priceText: String = "",
    val totalPriceFormatted: String = "",
    val productQuantity: Int = 0,
    val addToCartTitle: String = ""
)
