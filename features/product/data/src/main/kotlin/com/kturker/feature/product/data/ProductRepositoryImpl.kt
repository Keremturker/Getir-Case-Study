package com.kturker.feature.product.data

import com.kturker.contract.Dispatchers
import com.kturker.contract.ResultState
import com.kturker.database.room.dao.ProductDao
import com.kturker.database.room.dao.SuggestedProductDao
import com.kturker.feature.product.data.common.fetchAndCacheFromRoomAndApi
import com.kturker.feature.product.data.mapper.ProductMapper
import com.kturker.feature.product.data.mapper.SuggestedProductMapper
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.feature.product.domain.model.ProductItem
import com.kturker.network.asRestResult
import com.kturker.network.mapToResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    @Dispatchers.IO private val ioDispatcher: CoroutineDispatcher,
    private val service: ProductService,
    private val productMapper: ProductMapper,
    private val suggestedProductMapper: SuggestedProductMapper,
    private val productDao: ProductDao,
    private val suggestedProductDao: SuggestedProductDao
) : ProductRepository {

    override suspend fun getSuggestedProducts(): Flow<ResultState<List<ProductItem>>> =
        fetchAndCacheFromRoomAndApi(
            dispatcher = ioDispatcher,
            dbFlow = suggestedProductDao.getSuggestedProductFlow(),
            entityToModel = suggestedProductMapper::mapEntityListToItemList,
            apiCall = {
                service.getSuggestedProducts().asRestResult.mapToResultState {
                    it.firstOrNull()?.products.orEmpty()
                }
            },
            dtoToEntity = suggestedProductMapper::mapDtoListToItemList,
            insertToDb = { suggestedProductDao.insertSuggestedProducts(it) }
        )

    override suspend fun getProducts(): Flow<ResultState<List<ProductItem>>> =
        fetchAndCacheFromRoomAndApi(
            dispatcher = ioDispatcher,
            dbFlow = productDao.getProductsFlow(),
            entityToModel = productMapper::mapEntityListToItemList,
            apiCall = {
                service.getProducts().asRestResult.mapToResultState {
                    it.firstOrNull()?.products.orEmpty()
                }
            },
            dtoToEntity = productMapper::mapDtoListToItemList,
            insertToDb = { productDao.insertProducts(it) }
        )
}