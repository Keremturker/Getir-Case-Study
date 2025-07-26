package com.kturker.feature.product.data.common

import com.kturker.contract.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

internal fun <Dto, Entity> fetchAndCacheFromApiOnly(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> ResultState<List<Dto>>,
    dtoToEntity: (List<Dto>) -> List<Entity>,
    insertToDb: suspend (List<Entity>) -> Unit
): Flow<ResultState<Unit>> = flow {
    val result = withContext(context = dispatcher) {
        runCatching {
            when (val response = apiCall()) {
                is ResultState.Success -> {
                    val entities = dtoToEntity(response.data)
                    insertToDb(entities)
                    ResultState.Success(data = Unit)
                }

                is ResultState.Error -> response
            }
        }.getOrElse { throwable ->
            ResultState.Error(message = throwable.message ?: "Unknown error")
        }
    }

    emit(value = result)
}