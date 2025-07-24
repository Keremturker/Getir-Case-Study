package com.kturker.core

import com.kturker.contract.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

inline fun <reified T> Flow<ResultState<T>>.buildDefaultFlow(
    dispatcher: CoroutineDispatcher
): Flow<ResultState<T>> {
    return this.catch { e ->
        emit(ResultState.Error(message = e.message.orEmpty()))
    }.flowOn(dispatcher)
}

