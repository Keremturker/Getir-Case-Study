package com.kturker.feature.cart.domain.usecase

import com.kturker.core.domain.CartRepository
import com.kturker.core.domain.model.CartItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartProductsUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke(): Flow<List<CartItem>> = repository.getCartItems()
}
