package com.kturker.feature.product.presentation.list

import androidx.compose.runtime.Immutable

@Immutable
internal data class ProductListUiState(
    val title: String = "",
    val totalPriceFormatted: String = "",
    val goToCartButtonTitle: String = "",
    val emptyListText: String = "",
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false
)
