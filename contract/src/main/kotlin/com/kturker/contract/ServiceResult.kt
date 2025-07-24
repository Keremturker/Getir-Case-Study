package com.kturker.contract

sealed interface ServiceResult<out T> {
    data class Success<out T>(val data: T) : ServiceResult<T>
    data class Error(val message: String) : ServiceResult<Nothing>
}

fun <T : Any, R : Any> ServiceResult<T>.mapToResultState(block: (T) -> R): ServiceResult<R> {
    return when (this) {
        is ServiceResult.Error -> {
            ServiceResult.Error(message = message )
        }

        is ServiceResult.Success -> {
            ServiceResult.Success(data = block(this.data))
        }
    }
}