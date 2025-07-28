package com.kturker.feature.cart.presentation.cart

internal sealed class DialogEvent {
    data class ShowClearCartDialog(
        val onPositive: () -> Unit
    ) : DialogEvent()

    data class ShowCompleteOrderDialog(
        val totalPrice: String,
        val onPositive: () -> Unit
    ) : DialogEvent()
}
