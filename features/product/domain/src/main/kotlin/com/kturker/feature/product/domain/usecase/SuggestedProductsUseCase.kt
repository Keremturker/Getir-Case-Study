package com.kturker.feature.product.domain.usecase

import com.kturker.contract.Dispatchers
import com.kturker.feature.product.domain.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SuggestedProductsUseCase @Inject constructor(
    @Dispatchers.IO private val dispatcher: CoroutineDispatcher,
    private val repository: ProductRepository
) {

    suspend operator fun invoke() = withContext(context = dispatcher) {
        repository.fetchSuggestedProducts()
    }
}
