package com.kturker.feature.cart.presentation.cart

import androidx.compose.runtime.Immutable
import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem

@Immutable
internal data class CartUiState(
    val totalPriceFormatted: String = "",
    val cartProducts: List<CartItem> = listOf(),
    val suggestedProducts: List<ProductItem> = listOf()
)
