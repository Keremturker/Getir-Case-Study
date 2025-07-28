package com.kturker.feature.product.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
internal fun ProductDetailRouteScreen(viewmodel: ProductDetailViewModel = hiltViewModel()) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewmodel.cartTotalPrice
            .distinctUntilChanged()
            .collect { cartTotalPrice ->
                viewmodel.updateCartTotalPrice(totalPrice = cartTotalPrice)
            }
    }

    LaunchedEffect(key1 = Unit) {
        viewmodel.quantityById
            .distinctUntilChanged()
            .collect { quantity ->
                viewmodel.updateProductQuantity(quantity = quantity)
            }
    }

    ProductDetailScreen(state = uiState, action = viewmodel)
}
