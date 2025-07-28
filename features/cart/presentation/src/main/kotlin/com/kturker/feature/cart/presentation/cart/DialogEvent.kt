package com.kturker.feature.cart.presentation.cart

internal sealed class DialogEvent {
    data class ShowDialog(
        val description: String,
        val positiveButtonText: String,
        val negativeButtonText: String? = null,
        val onPositive: () -> Unit
    ) : DialogEvent()
}
