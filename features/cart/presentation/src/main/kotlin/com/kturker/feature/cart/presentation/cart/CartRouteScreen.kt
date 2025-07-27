package com.kturker.feature.cart.presentation.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kturker.uikit.components.KtBasicDialog
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
internal fun CartRoutScreen(viewmodel: CartViewModel = hiltViewModel()) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    val dialogState = remember { mutableStateOf<DialogEvent?>(null) }

    LaunchedEffect(Unit) {
        viewmodel.dialogEvents.collect { event ->
            dialogState.value = event
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewmodel.cartTotalPrice
            .distinctUntilChanged()
            .collect { cartTotalPrice ->
                viewmodel.updateCartTotalPrice(totalPrice = cartTotalPrice)
            }
    }

    LaunchedEffect(key1 = Unit) {
        viewmodel.suggestedProduct
            .distinctUntilChanged()
            .collect { suggestedProduct ->
                viewmodel.updateSuggestedProduct(suggestedProducts = suggestedProduct)
            }
    }

    LaunchedEffect(key1 = Unit) {
        viewmodel.cartProducts
            .distinctUntilChanged()
            .collect { cartProducts ->
                viewmodel.updateCartProducts(cartProducts = cartProducts)
            }
    }

    DialogHandler(dialogState = dialogState)

    CartScreen(uiState = uiState, action = viewmodel)
}

@Composable
private fun DialogHandler(dialogState: MutableState<DialogEvent?>) {
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