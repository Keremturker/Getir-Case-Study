package com.kturker.feature.product.data

import com.kturker.contract.Dispatchers
import com.kturker.contract.ResultState
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.feature.product.domain.model.AllProductModel
import com.kturker.feature.product.domain.model.AllSuggestedProductModel
import com.kturker.feature.product.mapper.AllProductMapper
import com.kturker.feature.product.mapper.AllSuggestedProductMapper
import com.kturker.network.BaseRepository
import com.kturker.network.mapToResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    @Dispatchers.IO private val ioDispatcher: CoroutineDispatcher,
    private val service: ProductService,
    private val allProductMapper: AllProductMapper,
    private val allSuggestedProductMapper: AllSuggestedProductMapper
) : ProductRepository, BaseRepository() {

    override suspend fun getAllProducts(): ResultState<List<AllProductModel>> =
        withContext(context = ioDispatcher) {
            return@withContext request {
                service.getAllProducts()
            }.mapToResultState {
                allProductMapper.map(response = it)
            }
        }

    override suspend fun getAllSuggestedProducts(): ResultState<List<AllSuggestedProductModel>> =
        withContext(context = ioDispatcher) {
            return@withContext request {
                service.getAllSuggestedProducts()
            }.mapToResultState {
                allSuggestedProductMapper.map(response = it)
            }
        }
}