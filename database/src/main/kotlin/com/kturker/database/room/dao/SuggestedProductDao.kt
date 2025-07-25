package com.kturker.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kturker.database.room.entity.SuggestedProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuggestedProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSuggestedProducts(products: List<SuggestedProductEntity>)

    @Query("SELECT * FROM SuggestedProductEntity")
    fun getSuggestedProductFlow(): Flow<List<SuggestedProductEntity>>

}