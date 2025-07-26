package com.kturker.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kturker.database.room.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM CartEntity")
    fun getCartItemsFlow(): Flow<List<CartEntity>>

    @Query("SELECT SUM(price * quantity) FROM CartEntity")
    fun getTotalCartPriceFlow(): Flow<Double>

    @Query("SELECT * FROM CartEntity WHERE id = :id")
    suspend fun getCartItemById(id: String): CartEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartEntity)

    @Update
    suspend fun update(cartItem: CartEntity)

    @Delete
    suspend fun delete(cartItem: CartEntity)

}