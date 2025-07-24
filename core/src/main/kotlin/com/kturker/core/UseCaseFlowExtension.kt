package com.kturker.core

import com.kturker.contract.RestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

inline fun <reified T> Flow<RestResult<T>>.buildDefaultFlow(
    dispatcher: CoroutineDispatcher,
    loading: Boolean = true
): Flow<RestResult<T>> {
    return this.onStart {
        emit(RestResult.Loading(loading))
    }.catch { e ->
        emit(RestResult.Error(e.message.orEmpty()))
    }.onCompletion {
        emit(RestResult.Loading(false))
    }.flowOn(dispatcher)
}
