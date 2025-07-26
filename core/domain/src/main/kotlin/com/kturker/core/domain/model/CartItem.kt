package com.kturker.core.domain.model

data class CartItem(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageURL: String = "",
    val price: Double = 0.0,
    val priceText: String = "",
    val quantity: Int
)
