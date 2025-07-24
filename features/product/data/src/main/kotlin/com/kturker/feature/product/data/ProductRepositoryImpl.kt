package com.kturker.feature.product.data

import com.kturker.contract.Dispatchers
import com.kturker.contract.ServiceResult
import com.kturker.contract.mapToResultState
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.feature.product.domain.model.AllProductModel
import com.kturker.feature.product.domain.model.AllSuggestedProductModel
import com.kturker.feature.product.mapper.AllProductMapper
import com.kturker.feature.product.mapper.AllSuggestedProductMapper
import com.kturker.network.BaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    @Dispatchers.IO private val ioDispatcher: CoroutineDispatcher,
    private val service: ProductService,
    private val allProductMapper: AllProductMapper,
    private val allSuggestedProductMapper: AllSuggestedProductMapper
) : ProductRepository, BaseRepository() {

    override suspend fun getAllProducts(): ServiceResult<List<AllProductModel>> =
        withContext(context = ioDispatcher) {
            return@withContext request {
                service.getAllProducts()
            }.mapToResultState {
                allProductMapper.map(response = it)
            }
        }

    override suspend fun getAllSuggestedProducts(): ServiceResult<List<AllSuggestedProductModel>> =
        withContext(context = ioDispatcher) {
            return@withContext request {
                service.getAllSuggestedProducts()
            }.mapToResultState {
                allSuggestedProductMapper.map(response = it)
            }
        }
}