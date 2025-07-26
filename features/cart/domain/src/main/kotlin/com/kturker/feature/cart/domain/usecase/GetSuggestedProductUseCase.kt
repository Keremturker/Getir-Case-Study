package com.kturker.feature.cart.domain.usecase

import com.kturker.core.domain.CartRepository
import com.kturker.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSuggestedProductUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke(): Flow<List<ProductItem>> = repository.getSuggestedProducts()
}
