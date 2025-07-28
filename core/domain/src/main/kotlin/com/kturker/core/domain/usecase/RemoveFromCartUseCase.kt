package com.kturker.core.domain.usecase

import com.kturker.core.domain.CartRepository
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(
    private val repository: CartRepository
) {

    suspend operator fun invoke(id: String) = repository.removeFromCart(id = id)
}
