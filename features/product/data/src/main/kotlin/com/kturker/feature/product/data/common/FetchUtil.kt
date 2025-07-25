package com.kturker.feature.product.data.common

import com.kturker.contract.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal suspend fun <Entity, Model, Dto> fetchAndCacheFromRoomAndApi(
    dispatcher: CoroutineDispatcher,
    dbFlow: Flow<List<Entity>>,
    entityToModel: (List<Entity>) -> List<Model>,
    apiCall: suspend () -> ResultState<List<Dto>>,
    dtoToEntity: (List<Dto>) -> List<Entity>,
    insertToDb: suspend (List<Entity>) -> Unit
): Flow<ResultState<List<Model>>> = withContext(dispatcher) {
    channelFlow {
        launch {
            dbFlow
                .map { list -> ResultState.Success(entityToModel(list)) }
                .distinctUntilChanged()
                .collect { send(it) }
        }

        runCatching {
            when (val response = apiCall()) {
                is ResultState.Success -> {
                    val entities = dtoToEntity(response.data)
                    insertToDb(entities)
                }

                is ResultState.Error -> send(response)
            }
        }.onFailure {
            send(ResultState.Error("Network failed: ${it.message.orEmpty()}"))
        }
    }
}