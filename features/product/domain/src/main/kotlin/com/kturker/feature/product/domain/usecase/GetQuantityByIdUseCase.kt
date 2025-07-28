package com.kturker.feature.product.domain.usecase

import com.kturker.core.domain.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuantityByIdUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke(id: String): Flow<Int> = repository.getQuantityById(id = id)
}
