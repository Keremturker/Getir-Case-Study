package com.kturker.feature.product.data.mapper

import com.kturker.database.room.entity.SuggestedProductEntity
import com.kturker.feature.product.data.model.SuggestedItemDto
import javax.inject.Inject

internal class SuggestedProductMapper @Inject constructor() {

    fun mapDtoListToItemList(response: List<SuggestedItemDto>?) = response?.map { itemDto ->
        SuggestedProductEntity(
            id = itemDto.id.orEmpty(),
            name = itemDto.name.orEmpty().trim(),
            price = itemDto.price ?: 0.0,
            priceText = itemDto.priceText.orEmpty().trim(),
            shortDescription = itemDto.shortDescription.orEmpty().trim(),
            imageURL = itemDto.imageURL.orEmpty(),
            squareThumbnailURL = itemDto.squareThumbnailURL.orEmpty(),
            status = itemDto.status ?: 0,
            category = itemDto.category.orEmpty(),
            unitPrice = itemDto.unitPrice ?: 0.0
        )
    }.orEmpty()
}
