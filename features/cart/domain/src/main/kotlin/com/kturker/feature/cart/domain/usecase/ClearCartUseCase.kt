package com.kturker.feature.cart.domain.usecase

import com.kturker.core.domain.CartRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke() = repository.clearCart()
}
