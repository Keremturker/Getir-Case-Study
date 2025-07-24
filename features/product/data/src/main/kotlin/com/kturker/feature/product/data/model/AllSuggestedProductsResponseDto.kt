package com.kturker.feature.product.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal data class AllSuggestedProductsResponseDto(
    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("products")
    val products: List<SuggestedItemDto>? = null
)

@Serializable
internal data class SuggestedItemDto(
    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("price")
    val price: Double? = null,

    @SerialName("priceText")
    val priceText: String? = null,

    @SerialName("imageURL")
    val imageURL: String? = null,

    @SerialName("shortDescription")
    val shortDescription: String? = null,

    @SerialName("squareThumbnailURL")
    val squareThumbnailURL: String? = null,

    @SerialName("thumbnailURL")
    val thumbnailURL: String? = null,

    @SerialName("status")
    val status: Int? = null,

    @SerialName("category")
    val category: String? = null,

    @SerialName("unitPrice")
    val unitPrice: Double? = null
)
