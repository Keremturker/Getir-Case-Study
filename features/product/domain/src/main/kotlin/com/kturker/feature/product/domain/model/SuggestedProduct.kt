package com.kturker.feature.product.domain.model

data class AllSuggestedProductModel(
    val id: String = "",
    val name: String = "",
    val products: List<SuggestedProduct> = listOf()
)

data class SuggestedProduct(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val priceText: String = "",
    val imageURL: String = "",
    val shortDescription: String = "",
    val squareThumbnailURL: String = "",
    val status: Int = 0,
    val category: String = "",
    val unitPrice: Double = 0.0
)