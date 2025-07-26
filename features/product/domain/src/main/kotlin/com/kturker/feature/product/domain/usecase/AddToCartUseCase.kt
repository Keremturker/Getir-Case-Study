package com.kturker.feature.product.domain.usecase

import com.kturker.core.domain.CartRepository
import com.kturker.core.domain.ProductItem
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(item: ProductItem) = repository.addToCart(item = item)
}
