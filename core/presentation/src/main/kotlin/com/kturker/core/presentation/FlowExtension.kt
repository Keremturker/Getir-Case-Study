package com.kturker.core.presentation

import com.kturker.contract.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<ResultState<T>>.onSuccess(action: suspend (T) -> Unit): Flow<ResultState<T>> {
    return transform { restResult ->
        if (restResult is ResultState.Success) {
            action.invoke(restResult.data)
        }
        emit(restResult)
    }
}

fun <T> Flow<ResultState<T>>.onError(action: suspend (String) -> Unit): Flow<ResultState<T>> {
    return transform { restResult ->
        if (restResult is ResultState.Error) {
            action.invoke(restResult.message)
        }
        emit(restResult)
    }
}
