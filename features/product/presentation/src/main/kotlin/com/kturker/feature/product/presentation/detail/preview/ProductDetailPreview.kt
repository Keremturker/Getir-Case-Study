package com.kturker.feature.product.presentation.detail.preview

import androidx.compose.runtime.Composable
import com.kturker.core.presentation.KtPreviewWrapper
import com.kturker.core.presentation.PreviewGetir
import com.kturker.feature.product.presentation.detail.ProductDetailAction
import com.kturker.feature.product.presentation.detail.ProductDetailScreen
import com.kturker.feature.product.presentation.detail.ProductDetailUiState

@PreviewGetir
@Composable
private fun ProductDetailPreview() {
    KtPreviewWrapper {
        ProductDetailScreen(
            state = dummyUiState,
            action = dummyAction
        )
    }
}

@PreviewGetir
@Composable
private fun ProductDetailPreviewWithQuantity() {
    KtPreviewWrapper {
        ProductDetailScreen(
            state = dummyUiState.copy(productQuantity = 0),
            action = dummyAction
        )
    }
}

private val dummyAction = object : ProductDetailAction {
    override fun navigateUp() = Unit
    override fun addToCart() = Unit
    override fun removeFromCart() = Unit
    override fun navigateToCartScreen() = Unit
}

private val dummyUiState = ProductDetailUiState(
    imageUrl = "https://cdn.getir.com/product/sample.jpg",
    name = "Ruffles Max",
    description = "Yoğurt ve Baharat Aromalı",
    priceText = "₺34,50",
    totalPriceFormatted = "₺103,50",
    productQuantity = 3
)
