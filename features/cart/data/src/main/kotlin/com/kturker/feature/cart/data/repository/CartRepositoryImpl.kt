package com.kturker.feature.cart.data.repository

import android.annotation.SuppressLint
import com.kturker.core.domain.CartRepository
import com.kturker.core.domain.ProductItem
import com.kturker.database.room.dao.CartDao
import com.kturker.feature.cart.data.mapper.toCartItemEntity
import com.kturker.feature.cart.data.mapper.toProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {

    override fun getCartItems(): Flow<List<ProductItem>> =
        cartDao.getCartItemsFlow().map { list ->
            list.map { it.toProductItem() }
        }

    @SuppressLint("DefaultLocale")
    override fun getCartTotalPriceFlow(): Flow<String> {
        return cartDao.getTotalCartPriceFlow()
            .map { total ->
                String.format("₺%.2f", total).takeIf { total > 0.0 }.orEmpty()
            }
    }

    override fun getQuantityById(id: String): Flow<Int> {
        return cartDao.getQuantityById(id)
    }

    override suspend fun addToCart(item: ProductItem) {
        val existing = cartDao.getCartItemById(item.id)
        val updated = if (existing == null) {
            item.copy(cartCount = 1)
        } else {
            item.copy(cartCount = existing.quantity + 1)

        }
        cartDao.insert(updated.toCartItemEntity())
    }

    override suspend fun removeFromCart(id: String) {
        val existing = cartDao.getCartItemById(id) ?: return
        val newQuantity = existing.quantity - 1
        if (newQuantity > 0) {
            cartDao.update(existing.copy(quantity = newQuantity))
        } else {
            cartDao.delete(existing)
        }
    }
}