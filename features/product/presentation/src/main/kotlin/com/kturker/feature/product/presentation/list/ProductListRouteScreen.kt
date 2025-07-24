package com.kturker.feature.product.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ProductListRouteScreen(
    viewmodel: ProductListViewmodel = hiltViewModel()
) {

    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    ProductListScreen(state = uiState)
}