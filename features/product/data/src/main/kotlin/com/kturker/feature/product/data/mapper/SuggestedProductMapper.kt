package com.kturker.feature.product.data.mapper

import com.kturker.database.room.entity.SuggestedProductEntity
import com.kturker.feature.product.data.model.SuggestedItemDto
import com.kturker.feature.product.domain.model.SuggestedProductItem
import javax.inject.Inject

internal class SuggestedProductMapper @Inject constructor() {

    fun mapDtoListToItemList(response: List<SuggestedItemDto>?) = response?.map { itemDto ->
        SuggestedProductEntity(
            id = itemDto.id.orEmpty(),
            name = itemDto.name.orEmpty(),
            price = itemDto.price ?: 0.0,
            priceText = itemDto.priceText.orEmpty(),
            shortDescription = itemDto.shortDescription.orEmpty(),
            imageURL = itemDto.imageURL.orEmpty(),
            squareThumbnailURL = itemDto.squareThumbnailURL.orEmpty(),
            status = itemDto.status ?: 0,
            category = itemDto.category.orEmpty(),
            unitPrice = itemDto.unitPrice ?: 0.0
        )
    }.orEmpty()

    fun mapEntityListToItemList(items: List<SuggestedProductEntity>) = items.map { item ->
        SuggestedProductItem(
            id = item.id,
            name = item.name,
            price = item.price,
            priceText = item.priceText,
            shortDescription = item.shortDescription,
            imageURL = item.imageURL,
            squareThumbnailURL = item.squareThumbnailURL,
            status = item.status,
            category = item.category,
            unitPrice = item.unitPrice
        )
    }
}
