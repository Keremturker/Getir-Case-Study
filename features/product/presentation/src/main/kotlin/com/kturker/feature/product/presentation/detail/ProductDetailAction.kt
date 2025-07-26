package com.kturker.feature.product.presentation.detail

internal interface ProductDetailAction {
    fun navigateUp()
    fun addToCart()
    fun removeFromCart()
    fun navigateToCartScreen()
}