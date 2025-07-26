package com.kturker.feature.cart.data.mapper

import com.kturker.core.domain.ProductItem
import com.kturker.database.room.entity.CartEntity

internal fun CartEntity.toProductItem(): ProductItem {
    return ProductItem(
        id = id,
        name = name,
        price = price,
        cartCount = quantity,
        description = description,
        imageUrl = imageURL,
        priceText = priceText
    )
}

internal fun ProductItem.toCartItemEntity(): CartEntity {
    return CartEntity(
        id = id,
        name = name,
        price = price,
        quantity = cartCount,
        description = description,
        imageURL = imageUrl,
        priceText = priceText
    )
}