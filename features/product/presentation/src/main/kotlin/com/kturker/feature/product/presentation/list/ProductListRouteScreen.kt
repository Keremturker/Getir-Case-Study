package com.kturker.feature.product.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kturker.core.domain.model.ProductItem
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
internal fun ProductListRouteScreen(
    viewmodel: ProductListViewmodel = hiltViewModel()
) {

    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    val products: LazyPagingItems<ProductItem> =
        viewmodel.productList.collectAsLazyPagingItems()

    val suggestedProducts: LazyPagingItems<ProductItem> =
        viewmodel.suggestedProductList.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit) {
        viewmodel.totalPriceFormatted
            .distinctUntilChanged()
            .collect { totalPriceFormatted ->
                viewmodel.updateTotalPrice(totalPrice = totalPriceFormatted)
            }
    }

    ProductListScreen(
        state = uiState,
        action = viewmodel,
        snackbarFlow = viewmodel.snackbarMessage,
        products = products,
        suggestedProducts = suggestedProducts
    )
}