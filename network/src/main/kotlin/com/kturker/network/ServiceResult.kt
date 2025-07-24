package com.kturker.network

import com.kturker.contract.ResultState

sealed interface ServiceResult<out T> {
    data class Success<out T>(val data: T) : ServiceResult<T>
    data class Error(val message: String) : ServiceResult<Nothing>
}

fun <T : Any, R : Any> ServiceResult<T>.mapToResultState(block: (T) -> R): ResultState<R> {
    return when (this) {
        is ServiceResult.Error -> {
            ResultState.Error(message = message )
        }

        is ServiceResult.Success -> {
            ResultState.Success(data = block(this.data))
        }
    }
}

fun <T : Any> ServiceResult<T>.mapToResultState(): ResultState<Unit> {
    return mapToResultState {}
}
