package com.kturker.database.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kturker.database.room.ProductWithCart
import com.kturker.database.room.entity.SuggestedProductEntity

@Dao
interface SuggestedProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSuggestedProducts(products: List<SuggestedProductEntity>)

    @Query("""
SELECT 
    product.id AS id,
    product.name AS name,
    product.shortDescription AS description,
    COALESCE(NULLIF(product.imageURL, ''), product.squareThumbnailURL) AS imageURL,
    product.price AS price,
    product.priceText AS priceText,
    COALESCE(cart.quantity, 0) AS quantity
FROM SuggestedProductEntity AS product
LEFT JOIN CartEntity AS cart ON product.id = cart.id
""")
    fun getProductsWithCart(): PagingSource<Int, ProductWithCart>

}