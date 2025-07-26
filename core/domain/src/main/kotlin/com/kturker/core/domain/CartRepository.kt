package com.kturker.core.domain

import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    fun getSuggestedProducts(): Flow<List<ProductItem>>
    fun getCartTotalPriceFlow(): Flow<String>
    fun getQuantityById(id: String): Flow<Int>
    suspend fun addToCart(item: ProductItem)
    suspend fun removeFromCart(id: String)
    fun clearCart()
}