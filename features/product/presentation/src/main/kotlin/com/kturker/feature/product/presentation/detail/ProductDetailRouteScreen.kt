package com.kturker.feature.product.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ProductDetailRouteScreen(
    viewmodel: ProductDetailViewmodel = hiltViewModel()
) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    ProductDetailScreen(state = uiState, action = viewmodel)
}