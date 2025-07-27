package com.kturker.core.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProductItem(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val price: Double = 0.0,
    val priceText: String = "",
    val cartCount: Int = 0
)