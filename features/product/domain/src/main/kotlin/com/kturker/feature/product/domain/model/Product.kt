package com.kturker.feature.product.domain.model

data class AllProductModel(
    val id: String = "",
    val name: String = "",
    val productCount: Int = 0,
    val products: List<Product> = listOf()
)

data class Product(
    val id: String = "",
    val name: String = "",
    val attribute: String = "",
    val shortDescription: String = "",
    val thumbnailURL: String = "",
    val imageURL: String = "",
    val price: Double = 0.0,
    val priceText: String = ""
)