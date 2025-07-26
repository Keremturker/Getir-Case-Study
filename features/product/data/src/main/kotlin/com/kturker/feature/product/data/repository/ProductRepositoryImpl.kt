package com.kturker.feature.product.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kturker.contract.Dispatchers
import com.kturker.contract.ResultState
import com.kturker.core.domain.model.ProductItem
import com.kturker.database.room.ProductWithCart
import com.kturker.database.room.dao.ProductDao
import com.kturker.database.room.dao.SuggestedProductDao
import com.kturker.feature.product.data.ProductService
import com.kturker.feature.product.data.common.fetchAndCacheFromApiOnly
import com.kturker.feature.product.data.mapper.ProductMapper
import com.kturker.feature.product.data.mapper.ProductWithCardMapper
import com.kturker.feature.product.data.mapper.SuggestedProductMapper
import com.kturker.feature.product.domain.ProductRepository
import com.kturker.network.asRestResult
import com.kturker.network.mapToResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    @Dispatchers.IO private val ioDispatcher: CoroutineDispatcher,
    private val service: ProductService,
    private val productMapper: ProductMapper,
    private val suggestedProductMapper: SuggestedProductMapper,
    private val productDao: ProductDao,
    private val suggestedProductDao: SuggestedProductDao,
    private val productWithCardMapper: ProductWithCardMapper
) : ProductRepository {

    override suspend fun fetchSuggestedProducts(): Flow<ResultState<Unit>> =
        fetchAndCacheFromApiOnly(
            dispatcher = ioDispatcher,
            apiCall = {
                service.getSuggestedProducts().asRestResult.mapToResultState {
                    it.firstOrNull()?.products.orEmpty()
                }
            },
            dtoToEntity = suggestedProductMapper::mapDtoListToItemList,
            insertToDb = { suggestedProductDao.insertSuggestedProducts(it) }
        )

    override fun getProductsPaging(): Flow<PagingData<ProductItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 20
        )
    ) { productDao.getProductsWithCart() }
        .flow
        .map { data: PagingData<ProductWithCart> ->
            productWithCardMapper.mapProductWithCartToProductItem(data = data)
        }

    override fun getSuggestedProductsPaging(): Flow<PagingData<ProductItem>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10
        )
    ) { suggestedProductDao.getProductsWithCart() }
        .flow
        .map { data: PagingData<ProductWithCart> ->
            productWithCardMapper.mapProductWithCartToProductItem(data = data)
        }

    override suspend fun fetchProducts(): Flow<ResultState<Unit>> =
        fetchAndCacheFromApiOnly(
            dispatcher = ioDispatcher,
            apiCall = {
                service.getProducts().asRestResult.mapToResultState {
                    it.firstOrNull()?.products.orEmpty()
                }
            },
            dtoToEntity = productMapper::mapDtoListToItemList,
            insertToDb = productDao::insertProducts
        )
}