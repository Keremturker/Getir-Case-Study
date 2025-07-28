package com.kturker.feature.product.presentation.list.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kturker.core.domain.model.ProductItem
import com.kturker.feature.product.presentation.list.ProductListAction
import com.kturker.feature.product.presentation.list.ProductListScreen
import com.kturker.feature.product.presentation.list.ProductListUiState
import com.kturker.uikit.extension.KtPreviewWrapper
import com.kturker.uikit.extension.PreviewGetir
import kotlinx.coroutines.flow.flowOf

@PreviewGetir
@Composable
private fun ProductListScreenNotEmptyPreview() {
    val state = ProductListUiState(
        title = "Ürünler",
        totalPriceFormatted = "3 TL",
        goToCartButtonTitle = "Sepete Git",
        emptyListText = "Item Bulunamadi"
    )

    KtPreviewWrapper {
        ProductListScreen(
            state = state,
            action = dummyAction,
            snackbarFlow = flowOf(),
            products = fakeLazyPagingItems(),
            suggestedProducts = fakeLazyPagingItems()
        )
    }
}

@PreviewGetir
@Composable
private fun ProductListScreenEmptyPreview() {
    val state = ProductListUiState(
        title = "Ürünler",
        totalPriceFormatted = "",
        goToCartButtonTitle = "Sepete Git",
        emptyListText = "Item Bulunamadi"
    )

    KtPreviewWrapper {
        ProductListScreen(
            state = state,
            action = dummyAction,
            snackbarFlow = flowOf(),
            products = fakeLazyEmptyPagingItems(),
            suggestedProducts = fakeLazyEmptyPagingItems()
        )
    }
}

@Composable
private fun fakeLazyEmptyPagingItems(): LazyPagingItems<ProductItem> {
    val emptyFlow = remember { flowOf(PagingData.empty<ProductItem>()) }
    return emptyFlow.collectAsLazyPagingItems()
}

@Composable
private fun fakeLazyPagingItems(): LazyPagingItems<ProductItem> {
    val dummyProducts = List(15) { index ->
        ProductItem(
            id = (index + 1).toString(),
            name = "Ürün ${(index + 1)}",
            description = "Açıklama ${(index + 1)}",
            imageUrl = "",
            price = 10.0 + index,
            priceText = "₺${"%.2f".format(10.0 + index)}",
            quantity = 0
        )
    }

    val fakeFlow = remember {
        flowOf(PagingData.from(dummyProducts))
    }

    return fakeFlow.collectAsLazyPagingItems()
}

private val dummyAction = object : ProductListAction {
    override fun addToCart(item: ProductItem) = Unit
    override fun removeFromCart(item: ProductItem) = Unit
    override fun onFetchData(defaultIsRefreshing: Boolean) = Unit
    override fun navigateToDetailScreen(item: ProductItem) = Unit
    override fun navigateToCartScreen() = Unit
}
