package com.kturker.feature.cart.presentation.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kturker.uikit.components.KtBasicDialog

@Composable
internal fun CartRoutScreen(viewmodel: CartViewModel = hiltViewModel()) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    val dialogState = remember { mutableStateOf<DialogEvent?>(null) }

    CartScreen(uiState = uiState, action = viewmodel)

    LaunchedEffect(Unit) {
        viewmodel.dialogEvents.collect { event ->
            dialogState.value = event
        }
    }

    fun onDismissDialog() {
        dialogState.value = null
    }

    dialogState.value?.let { event ->
        when (event) {
            is DialogEvent.ShowDialog -> {
                KtBasicDialog(
                    onDismissRequest = ::onDismissDialog,
                    description = event.description,
                    positiveButtonTitle = event.positiveButtonText,
                    negativeButtonTitle = event.negativeButtonText,
                    positiveButtonAction = {
                        event.onPositive.invoke()
                        onDismissDialog()
                    },
                    negativeButtonAction = {
                        onDismissDialog()
                    }
                )
            }
        }
    }
}