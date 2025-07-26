package com.kturker.core.domain

import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<ProductItem>>
    fun getCartTotalPriceFlow(): Flow<String>
    fun getQuantityById(id: String): Flow<Int>
    suspend fun addToCart(item: ProductItem)
    suspend fun removeFromCart(id: String)
}