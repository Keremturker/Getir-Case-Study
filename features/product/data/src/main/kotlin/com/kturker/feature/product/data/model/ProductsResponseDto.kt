package com.kturker.feature.product.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal data class ProductsResponseDto(
    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("productCount")
    val productCount: Int? = null,

    @SerialName("products")
    val products: List<ProductItemDto>? = null
)

@Serializable
internal data class ProductItemDto(
    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("attribute")
    val attribute: String? = null,

    @SerialName("shortDescription")
    val shortDescription: String? = null,

    @SerialName("thumbnailURL")
    val thumbnailURL: String? = null,

    @SerialName("imageURL")
    val imageURL: String? = null,

    @SerialName("price")
    val price: Double? = null,

    @SerialName("priceText")
    val priceText: String? = null
)
