package com.kturker.core

import com.kturker.contract.RestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<RestResult<T>>.onSuccess(action: suspend (T) -> Unit): Flow<RestResult<T>> {
    return transform { restResult ->
        if (restResult is RestResult.Success) {
            action.invoke(restResult.result)
        }
        emit(restResult)
    }
}

fun <T> Flow<RestResult<T>>.onError(action: suspend (String) -> Unit): Flow<RestResult<T>> {
    return transform { restResult ->
        if (restResult is RestResult.Error) {
            action.invoke(restResult.error)
        }
        emit(restResult)
    }
}
