package com.kturker.core

import androidx.lifecycle.ViewModel
import com.kturker.contract.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform

abstract class CoreViewModel : ViewModel() {

    fun <T : Any> safeFlowApiCall(
        call: () -> Flow<ResultState<T>>
    ): Flow<ResultState<T>> {
        return call.invoke()
            .transform { emit(it) }
            .catch { emit(value = ResultState.Error(message = it.message.orEmpty())) }
    }

}