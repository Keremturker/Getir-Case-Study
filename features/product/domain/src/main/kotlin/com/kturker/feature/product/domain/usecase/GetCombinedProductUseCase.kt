package com.kturker.feature.product.domain.usecase

import com.kturker.contract.Dispatchers
import com.kturker.contract.ResultState
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.feature.product.domain.model.CombineAllProduct
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCombinedProductUseCase @Inject constructor(
    @Dispatchers.IO private val dispatcher: CoroutineDispatcher,
    private val repository: ProductRepository
) {

    suspend operator fun invoke() = withContext(context = dispatcher) {
        val productsDeferred = async { repository.getAllProducts() }
        val suggestedDeferred = async { repository.getAllSuggestedProducts() }

        val products = (productsDeferred.await() as? ResultState.Success)?.data
        val suggested = (suggestedDeferred.await() as? ResultState.Success)?.data

        CombineAllProduct(
            products = products,
            suggestedProduct = suggested
        )
    }
}
