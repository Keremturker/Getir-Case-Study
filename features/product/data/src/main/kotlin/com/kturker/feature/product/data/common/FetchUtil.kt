package com.kturker.feature.product.data.common

import com.kturker.contract.ResultState
import com.kturker.database.room.entity.CartEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal suspend fun <Entity, Model, Dto> fetchAndCacheFromRoomAndApi(
    dispatcher: CoroutineDispatcher,
    dbFlow: Flow<List<Entity>>,
    cartItemsFlow: Flow<List<CartEntity>>,
    entityToModel: (List<Entity>) -> List<Model>,
    mergeWithCart: (List<Model>, List<CartEntity>) -> List<Model>,
    apiCall: suspend () -> ResultState<List<Dto>>,
    dtoToEntity: (List<Dto>) -> List<Entity>,
    insertToDb: suspend (List<Entity>) -> Unit
): Flow<ResultState<List<Model>>> = withContext(context = dispatcher) {
    channelFlow {
        launch {
            combine(
                flow = dbFlow.map(transform = entityToModel),
                flow2 = cartItemsFlow
            ) { models, cartItems ->
                val merged = mergeWithCart(models, cartItems)
                ResultState.Success(data = merged)
            }.distinctUntilChanged()
                .collect { send(element = it) }
        }

        runCatching {
            when (val response = apiCall()) {
                is ResultState.Success -> {
                    val entities = dtoToEntity(response.data)
                    insertToDb(entities)
                }

                is ResultState.Error -> send(element = response)
            }
        }.onFailure {
            send(element = ResultState.Error(message = "Network failed: ${it.message.orEmpty()}"))
        }
    }
}