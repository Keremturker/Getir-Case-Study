package com.kturker.feature.cart.data.mapper

import com.kturker.core.domain.model.ProductItem
import com.kturker.database.room.ProductWithCart
import javax.inject.Inject

internal class SuggestedProductMapper @Inject constructor() {

    fun mapProductWithCartToProductItem(data: List<ProductWithCart>) =
        data.map { entity: ProductWithCart ->
            ProductItem(
                id = entity.id,
                name = entity.name.trim(),
                description = entity.description,
                imageUrl = entity.imageURL,
                price = entity.price,
                priceText = entity.priceText.trim(),
                quantity = entity.quantity
            )
        }
}
