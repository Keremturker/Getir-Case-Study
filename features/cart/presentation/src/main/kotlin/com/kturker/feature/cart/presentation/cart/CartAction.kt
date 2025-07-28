package com.kturker.feature.cart.presentation.cart

internal interface CartAction {
    fun navigateUp()
    fun clearCartDialog()
    fun addToCard(id: String)
    fun removeFromCard(id: String)
    fun completeOrderDialog()
    fun navigateToDetail(id: String)
}
