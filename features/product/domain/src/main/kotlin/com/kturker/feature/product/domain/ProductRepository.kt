package com.kturker.feature.product.domain

import androidx.paging.PagingData
import com.kturker.contract.ResultState
import com.kturker.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProducts(): Flow<ResultState<Unit>>

    suspend fun fetchSuggestedProducts(): Flow<ResultState<Unit>>

    fun getProductsPaging(): Flow<PagingData<ProductItem>>

    fun getSuggestedProductsPaging(): Flow<PagingData<ProductItem>>

}