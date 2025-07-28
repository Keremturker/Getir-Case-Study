package com.kturker.feature.cart.presentation.cart.preview

import androidx.compose.runtime.Composable
import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem
import com.kturker.core.presentation.KtPreviewWrapper
import com.kturker.core.presentation.PreviewGetir
import com.kturker.feature.cart.presentation.cart.CartAction
import com.kturker.feature.cart.presentation.cart.CartScreen
import com.kturker.feature.cart.presentation.cart.CartUiState

@PreviewGetir
@Composable
private fun CartScreenWithItemsPreview() {
    KtPreviewWrapper {
        CartScreen(
            uiState = dummyUiState,
            action = dummyAction
        )
    }
}

@PreviewGetir
@Composable
private fun CartScreenEmptyPreview() {
    KtPreviewWrapper {
        CartScreen(
            uiState = dummyUiState.copy(suggestedProducts = listOf()),
            action = dummyAction
        )
    }
}

private val dummyAction = object : CartAction {
    override fun navigateUp() = Unit
    override fun clearCartDialog() = Unit
    override fun addToCard(id: String) = Unit
    override fun removeFromCard(id: String) = Unit
    override fun completeOrderDialog() = Unit
    override fun navigateToDetail(id: String) = Unit
}

private val dummyUiState = CartUiState(
    totalPriceFormatted = "₺103,50",
    cartProducts = listOf(
        CartItem(
            id = "1",
            name = "Doritos Risk",
            description = "Süper Baharatlı",
            imageURL = "",
            price = 29.99,
            priceText = "₺29,99",
            quantity = 1
        ),
        CartItem(
            id = "2",
            name = "Ruffles Max",
            description = "Yoğurt ve Baharat",
            imageURL = "",
            price = 34.50,
            priceText = "₺34,50",
            quantity = 2
        )
    ),
    suggestedProducts = listOf(
        ProductItem(
            id = "3",
            name = "Lays",
            description = "Baharatlı Patates Cipsi",
            imageUrl = "",
            price = 17.25,
            priceText = "₺17,25",
            quantity = 0
        )
    )
)
