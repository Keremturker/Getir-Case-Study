package com.kturker.feature.product.domain.model

data class CombineAllProduct(
    val products: List<AllProductModel>? = null,
    val suggestedProduct: List<AllSuggestedProductModel>? = null
)