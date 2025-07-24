package com.kturker.feature.product.data

import com.kturker.contract.Dispatchers
import com.kturker.contract.RestResult
import com.kturker.contract.mapOnSuccess
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.feature.product.domain.model.AllProductModel
import com.kturker.feature.product.mapper.AllProductMapper
import com.kturker.network.BaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    @Dispatchers.IO private val ioDispatcher: CoroutineDispatcher,
    private val service: ProductService,
    private val allProductMapper: AllProductMapper
) : ProductRepository, BaseRepository() {

    override suspend fun getAllProducts(): RestResult<List<AllProductModel>> = withContext(ioDispatcher) {
        return@withContext request {
            service.getAllProducts()
        }.mapOnSuccess {
            allProductMapper.map(it)
        }
    }
}