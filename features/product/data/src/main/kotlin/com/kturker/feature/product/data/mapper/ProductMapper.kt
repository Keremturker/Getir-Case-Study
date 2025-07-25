package com.kturker.feature.product.data.mapper

import com.kturker.database.room.entity.ProductEntity
import com.kturker.feature.product.data.model.ProductItemDto
import com.kturker.feature.product.domain.model.ProductItem
import javax.inject.Inject

internal class ProductMapper @Inject constructor() {

    fun mapDtoListToItemList(response: List<ProductItemDto>?) = response?.map { itemDto ->
        ProductEntity(
            id = itemDto.id.orEmpty(),
            name = itemDto.name.orEmpty(),
            attribute = itemDto.attribute.orEmpty(),
            shortDescription = itemDto.shortDescription.orEmpty(),
            thumbnailURL = itemDto.thumbnailURL.orEmpty(),
            imageURL = itemDto.imageURL.orEmpty(),
            price = itemDto.price ?: 0.0,
            priceText = itemDto.priceText.orEmpty()
        )
    }.orEmpty()

    fun mapEntityListToItemList(items: List<ProductEntity>) = items.map { item ->
        ProductItem(
            id = item.id,
            name = item.name,
            attribute = item.attribute,
            shortDescription = item.shortDescription,
            thumbnailURL = item.thumbnailURL,
            imageURL = item.imageURL,
            price = item.price,
            priceText = item.priceText
        )
    }

}
