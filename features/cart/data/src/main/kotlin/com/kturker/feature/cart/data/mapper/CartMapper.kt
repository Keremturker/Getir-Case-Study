package com.kturker.feature.cart.data.mapper

import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem
import com.kturker.database.room.entity.CartEntity

internal fun ProductItem.toCartItemEntity(): CartEntity {
    return CartEntity(
        id = id,
        name = name,
        price = price,
        quantity = quantity,
        description = description,
        imageURL = imageUrl,
        priceText = priceText
    )
}

internal fun CartEntity.toCartItem(): CartItem {
    return CartItem(
        id = id,
        name = name,
        price = price,
        quantity = quantity,
        description = description,
        imageURL = imageURL,
        priceText = priceText
    )
}