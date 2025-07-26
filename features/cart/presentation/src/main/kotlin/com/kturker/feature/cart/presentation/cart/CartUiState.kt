package com.kturker.feature.cart.presentation.cart

import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem

internal data class CartUiState(
    val title: String = "",
    val completeOrderButtonTitle: String = "",
    val suggestedProductTitle: String = "",
    val totalPriceFormatted: String = "",
    val cartProducts: List<CartItem> = listOf(),
    val suggestedProducts: List<ProductItem> = listOf(),
)
