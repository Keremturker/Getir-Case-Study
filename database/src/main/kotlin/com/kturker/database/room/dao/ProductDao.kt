package com.kturker.database.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kturker.database.room.ProductWithCart
import com.kturker.database.room.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProducts(products: List<ProductEntity>)

    @Query("""
    SELECT 
        product.id AS id,
        product.name AS name,
        COALESCE(NULLIF(product.attribute, ''), product.shortDescription) AS description,
        COALESCE(NULLIF(product.imageURL, ''), product.thumbnailURL) AS imageURL,
        product.price AS price,
        product.priceText AS priceText,
        COALESCE(cart.quantity, 0) AS quantity
    FROM ProductEntity AS product
    LEFT JOIN CartEntity AS cart ON product.id = cart.id
""")
    fun getProductsWithCart(): PagingSource<Int, ProductWithCart>

}