package com.kturker.contract

sealed interface ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>
    data class Error(val message: String ) : ResultState<Nothing>
}

suspend fun <T> ResultState<T>.onSuccess(block: suspend (data: T) -> Unit): ResultState<T> {
    if (this is ResultState.Success) {
        block(this.data)
    }
    return this
}

suspend fun <T> ResultState<T>.onError(
    block: suspend (message: String) -> Unit
): ResultState<T> {
    if (this is ResultState.Error) {
        block(this.message)
    }
    return this
}
