package com.kturker.contract

sealed class RestResult<out T> {
    class Success<out T>(
        val result: T
    ) : RestResult<T>()

    data class Error(val error: String) : RestResult<Nothing>()

    class Loading(val loading: Boolean) : RestResult<Nothing>()
}

inline fun <T, R> RestResult<T>.mapOnSuccess(map: (T?) -> R): RestResult<R> = when (this) {
    is RestResult.Success -> RestResult.Success(map(result))
    is RestResult.Error -> this
    is RestResult.Loading -> this
}

inline fun <T> RestResult<T>.onError(action: (String) -> Unit): RestResult<T> {
    if (this is RestResult.Error) action(error)
    return this
}