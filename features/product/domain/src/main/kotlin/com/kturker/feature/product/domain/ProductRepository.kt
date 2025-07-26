package com.kturker.feature.product.domain

import com.kturker.contract.ResultState
import com.kturker.core.domain.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): Flow<ResultState<List<ProductItem>>>

    suspend fun getSuggestedProducts(): Flow<ResultState<List<ProductItem>>>

}