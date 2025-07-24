package com.kturker.feature.product.domain.usecase

import com.kturker.contract.Dispatchers
import com.kturker.contract.RestResult
import com.kturker.core.buildDefaultFlow
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.feature.product.domain.model.AllProductModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AllProductsUseCase @Inject constructor(
    @Dispatchers.IO private val ioDispatcher: CoroutineDispatcher,
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<RestResult<List<AllProductModel>>> = flow {
        val response = repository.getAllProducts()
        emit(response)
    }.buildDefaultFlow(ioDispatcher)
}
