package com.kturker.feature.product.domain.usecase

import com.kturker.contract.ResultState
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.feature.product.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<ResultState<List<ProductItem>>> = flow {
        emitAll(repository.getProducts())
    }
}
