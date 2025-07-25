package com.kturker.feature.product.domain.model

data class ProductItem(
    val id: String = "",
    val name: String = "",
    val attribute: String = "",
    val shortDescription: String = "",
    val thumbnailURL: String = "",
    val imageURL: String = "",
    val price: Double = 0.0,
    val priceText: String = ""
)