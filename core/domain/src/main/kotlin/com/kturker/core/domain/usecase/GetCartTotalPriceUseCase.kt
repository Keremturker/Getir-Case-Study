package com.kturker.core.domain.usecase

import com.kturker.core.domain.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartTotalPriceUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke(): Flow<String> = repository.getCartTotalPriceFlow()
}
