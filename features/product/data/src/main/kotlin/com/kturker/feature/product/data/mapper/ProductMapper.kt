package com.kturker.feature.product.data.mapper

import com.kturker.database.room.entity.ProductEntity
import com.kturker.feature.product.data.model.ProductItemDto
import javax.inject.Inject

internal class ProductMapper @Inject constructor() {

    fun mapDtoListToItemList(response: List<ProductItemDto>?) = response?.map { itemDto ->
        ProductEntity(
            id = itemDto.id.orEmpty(),
            name = itemDto.name.orEmpty().trim(),
            attribute = itemDto.attribute.orEmpty().trim(),
            shortDescription = itemDto.shortDescription.orEmpty().trim(),
            thumbnailURL = itemDto.thumbnailURL.orEmpty(),
            imageURL = itemDto.imageURL.orEmpty(),
            price = itemDto.price ?: 0.0,
            priceText = itemDto.priceText.orEmpty().trim()
        )
    }.orEmpty()

}
