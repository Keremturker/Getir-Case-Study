package com.kturker.feature.product.domain.usecase

import com.kturker.contract.ResultState
import com.kturker.core.domain.ProductItem
import com.kturker.feature.product.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SuggestedProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<ResultState<List<ProductItem>>> = flow {
        emitAll(repository.getSuggestedProducts())
    }
}
