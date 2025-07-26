package com.kturker.feature.product.presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.core.domain.ProductItem
import com.kturker.feature.product.presentation.component.BottomCartBar
import com.kturker.feature.product.presentation.component.CartPriceBadge
import com.kturker.feature.product.presentation.component.ProductItemCompose
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.scaffold.KtScaffold
import com.kturker.uikit.components.text.KtText
import com.kturker.uikit.components.toolbar.KtToolbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductListScreen(
    state: ProductListUiState,
    action: ProductListAction,
    snackbarFlow: Flow<String>
) {
    val color = LocalCustomColorsPalette.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        snackbarFlow.collectLatest { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    KtScaffold(
        topBar = {
            KtToolbar(centerContent = {
                KtText(
                    text = state.title,
                    color = color.textWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }, endContent = {
                if (state.totalPriceFormatted.isNotEmpty()) {
                    CartPriceBadge(priceText = state.totalPriceFormatted)
                }
            })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->

        PullToRefreshBox(
            modifier = Modifier.padding(paddingValues),
            isRefreshing = state.isRefreshing,
            onRefresh = action::onFetchData
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                ProductList(
                    items = state.productList,
                    suggestedProductList = state.suggestedProductList,
                    action = action
                )

                AnimatedVisibility(
                    visible = state.totalPriceFormatted.isNotEmpty(),
                    modifier = Modifier.align(Alignment.BottomCenter),
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight },
                        animationSpec = tween(durationMillis = 300)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> fullHeight },
                        animationSpec = tween(durationMillis = 300)
                    )
                ) {
                    BottomCartBar(
                        title = state.goToCartButtonTitle,
                        totalPriceFormatted = state.totalPriceFormatted,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Composable
private fun SuggestedProductList(
    items: List<ProductItem>,
    action: ProductListAction
) {
    val color = LocalCustomColorsPalette.current

    Box(
        Modifier
            .padding(top = 16.dp)
            .background(color = color.white)
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items, key = { _, item -> item.id }) { index, item ->
                ProductItemCompose(
                    modifier = Modifier
                        .padding(start = if (index == 0) 16.dp else 0.dp)
                        .width(100.dp),
                    item = item,
                    onMinusClick = {
                        action.removeFromCart(item)

                    },
                    onPlusClick = {
                        action.addToCart(item)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductList(
    items: List<ProductItem>,
    suggestedProductList: List<ProductItem>,
    action: ProductListAction
) {
    val color = LocalCustomColorsPalette.current

    Box(
        Modifier
            .padding(top = 16.dp)
            .background(color = color.white)
            .padding(top = 8.dp)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item(span = { GridItemSpan(3) }) {
                SuggestedProductList(
                    items = suggestedProductList,
                    action = action
                )
            }

            item(span = { GridItemSpan(3) }) {
                Spacer(
                    Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                        .background(color = color.backgroundColor)
                )
            }

            items(
                items = items,
                key = { item -> item.id }
            ) { item ->
                ProductItemCompose(
                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                    item = item,
                    onMinusClick = {
                        action.removeFromCart(item)

                    },
                    onPlusClick = {
                        action.addToCart(item)
                    }
                )
            }
        }
    }
}
