package com.kturker.feature.product.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.kturker.core.domain.ProductItem
import com.kturker.database.room.ProductWithCart
import javax.inject.Inject

internal class ProductWithCardMapper @Inject constructor() {

    fun mapProductWithCartToProductItem(data: PagingData<ProductWithCart>) =
        data.map { entity: ProductWithCart ->
            ProductItem(
                id = entity.id,
                name = entity.name.trim(),
                description = entity.description,
                imageUrl = entity.imageURL,
                price = entity.price,
                priceText = entity.priceText.trim(),
                cartCount = entity.quantity
            )
        }
}
