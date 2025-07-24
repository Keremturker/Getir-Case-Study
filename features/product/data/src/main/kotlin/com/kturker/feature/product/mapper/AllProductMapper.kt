package com.kturker.feature.product.mapper

import com.kturker.feature.product.data.model.AllProductsResponseDto
import com.kturker.feature.product.data.model.ProductItemDto
import com.kturker.feature.product.domain.model.AllProductModel
import com.kturker.feature.product.domain.model.Product
import javax.inject.Inject

internal class AllProductMapper @Inject constructor() {

    fun map(response: List<AllProductsResponseDto>?) = response?.map { model ->
        AllProductModel(
            id = model.id.orEmpty(),
            name = model.name.orEmpty(),
            productCount = model.productCount ?: 0,
            products = mapProduct(response = model.products)
        )
    }.orEmpty()

    private fun mapProduct(response: List<ProductItemDto>?) = response?.map { model ->
        Product(
            id = model.id.orEmpty(),
            name = model.name.orEmpty(),
            attribute = model.attribute.orEmpty(),
            shortDescription = model.shortDescription.orEmpty(),
            thumbnailURL = model.thumbnailURL.orEmpty(),
            imageURL = model.imageURL.orEmpty(),
            price = model.price ?: 0.0,
            priceText = model.priceText.orEmpty()
        )
    }.orEmpty()

}
