package com.kturker.feature.product.data.repository

import com.kturker.contract.Dispatchers
import com.kturker.contract.ResultState
import com.kturker.core.domain.ProductItem
import com.kturker.database.room.dao.CartDao
import com.kturker.database.room.dao.ProductDao
import com.kturker.database.room.dao.SuggestedProductDao
import com.kturker.feature.product.data.ProductService
import com.kturker.feature.product.data.common.fetchAndCacheFromRoomAndApi
import com.kturker.feature.product.data.mapper.ProductMapper
import com.kturker.feature.product.data.mapper.SuggestedProductMapper
import com.kturker.feature.product.domain.ProductRepository
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
    private val suggestedProductDao: SuggestedProductDao,
    private val cartDao: CartDao
) : ProductRepository {

    override suspend fun getSuggestedProducts(): Flow<ResultState<List<ProductItem>>> =
        fetchAndCacheFromRoomAndApi(
            dispatcher = ioDispatcher,
            dbFlow = suggestedProductDao.getSuggestedProductFlow(),
            cartItemsFlow = cartDao.getCartItemsFlow(),
            entityToModel = suggestedProductMapper::mapEntityListToItemList,
            mergeWithCart = { modelList, cartList ->
                modelList.map { model ->
                    val cartItem = cartList.find { it.id == model.id }
                    model.copy(cartCount = cartItem?.quantity ?: 0)
                }
            },
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
            cartItemsFlow = cartDao.getCartItemsFlow(),
            entityToModel = productMapper::mapEntityListToItemList,
            apiCall = {
                service.getProducts().asRestResult.mapToResultState {
                    it.firstOrNull()?.products.orEmpty()
                }
            },
            dtoToEntity = productMapper::mapDtoListToItemList,
            insertToDb = { productDao.insertProducts(it) },
            mergeWithCart = { modelList, cartList ->
                modelList.map { model ->
                    val cartItem = cartList.find { it.id == model.id }
                    model.copy(cartCount = cartItem?.quantity ?: 0)
                }
            }
        )
}