package com.kturker.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kturker.database.room.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM ProductEntity")
    fun getProductsFlow(): Flow<List<ProductEntity>>

}