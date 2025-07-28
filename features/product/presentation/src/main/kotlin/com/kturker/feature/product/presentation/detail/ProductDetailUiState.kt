package com.kturker.feature.product.presentation.detail

import androidx.compose.runtime.Immutable

@Immutable
internal data class ProductDetailUiState(
    val imageUrl: String = "",
    val name: String = "",
    val description: String = "",
    val priceText: String = "",
    val totalPriceFormatted: String = "",
    val productQuantity: Int = 0
)
