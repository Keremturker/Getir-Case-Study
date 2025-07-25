package com.kturker.feature.product.domain.usecase

import com.kturker.contract.ResultState
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.feature.product.domain.model.SuggestedProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SuggestedProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<ResultState<List<SuggestedProductItem>>> = flow {
        emitAll(repository.getSuggestedProducts())
    }
}
