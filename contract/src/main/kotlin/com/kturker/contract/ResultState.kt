package com.kturker.contract

sealed interface ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>
    data class Error(val message: String ) : ResultState<Nothing>
}
