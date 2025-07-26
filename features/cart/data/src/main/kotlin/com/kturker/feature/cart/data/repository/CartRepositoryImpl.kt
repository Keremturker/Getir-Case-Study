package com.kturker.feature.cart.data.repository

import android.annotation.SuppressLint
import com.kturker.core.domain.CartRepository
import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem
import com.kturker.database.room.dao.CartDao
import com.kturker.database.room.dao.SuggestedProductDao
import com.kturker.feature.cart.data.mapper.SuggestedProductMapper
import com.kturker.feature.cart.data.mapper.toCartItem
import com.kturker.feature.cart.data.mapper.toCartItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val suggestedProductDao: SuggestedProductDao,
    private val suggestedProductMapper: SuggestedProductMapper
) : CartRepository {

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
        val existing = cartDao.getCartItemById(id = item.id)
        val updated = if (existing == null) {
            item.copy(cartCount = 1)
        } else {
            item.copy(cartCount = existing.quantity + 1)
        }

        val cartItem = updated.toCartItemEntity()

        if (existing == null) {
            cartDao.insert(cartItem = cartItem)
        } else {
            cartDao.update(cartItem = cartItem)
        }
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

    override fun clearCart() {
        cartDao.clearCart()
    }

    override fun getCartItems(): Flow<List<CartItem>> = cartDao.getCartItemsFlow().map { list ->
        list.map { it.toCartItem() }
    }

    override fun getSuggestedProducts(): Flow<List<ProductItem>> {
        return suggestedProductDao.getSuggestedProductFlow().map { item ->
            suggestedProductMapper.mapProductWithCartToProductItem(item)
        }
    }
}