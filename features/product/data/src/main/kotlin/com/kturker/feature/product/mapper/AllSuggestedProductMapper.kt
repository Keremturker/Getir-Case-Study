package com.kturker.feature.product.mapper

import com.kturker.feature.product.data.model.AllSuggestedProductsResponseDto
import com.kturker.feature.product.data.model.SuggestedItemDto
import com.kturker.feature.product.domain.model.AllSuggestedProductModel
import com.kturker.feature.product.domain.model.SuggestedProduct
import javax.inject.Inject

internal class AllSuggestedProductMapper @Inject constructor() {

    fun map(response: List<AllSuggestedProductsResponseDto>?) = response?.map { model ->
        AllSuggestedProductModel(
            id = model.id.orEmpty(),
            name = model.name.orEmpty(),
            products = mapProduct(response = model.products)
        )
    }.orEmpty()

    private fun mapProduct(response: List<SuggestedItemDto>?) = response?.map { model ->
        SuggestedProduct(
            id = model.id.orEmpty(),
            name = model.name.orEmpty(),
            price = model.price ?: 0.0,
            priceText = model.priceText.orEmpty(),
            shortDescription = model.shortDescription.orEmpty(),
            imageURL = model.imageURL.orEmpty(),
            squareThumbnailURL = model.squareThumbnailURL.orEmpty(),
            status = model.status ?: 0,
            category = model.category.orEmpty(),
            unitPrice = model.unitPrice ?: 0.0,
        )
    }.orEmpty()

}
