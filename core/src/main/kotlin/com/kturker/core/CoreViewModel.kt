package com.kturker.core

import androidx.lifecycle.ViewModel
import com.kturker.contract.RestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform

abstract class CoreViewModel : ViewModel() {

    fun <T : Any> safeFlowApiCall(
        call: () -> Flow<RestResult<T>>
    ): Flow<RestResult<T>> {
        return call.invoke()
            .transform { emit(it) }
            .catch { emit(RestResult.Error(it.message.orEmpty())) }
    }

}