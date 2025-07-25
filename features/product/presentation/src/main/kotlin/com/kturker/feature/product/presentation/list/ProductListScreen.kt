package com.kturker.feature.product.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.feature.product.domain.model.ProductItem
import com.kturker.feature.product.presentation.component.ProductItemCompose
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.scaffold.KtScaffold
import com.kturker.uikit.components.text.KtText
import com.kturker.uikit.components.toolbar.KtToolbar

@Composable
internal fun ProductListScreen(
    state: ProductListUiState,
    action: ProductListActionHandler
) {
    val color = LocalCustomColorsPalette.current

    KtScaffold(
        topBar = {
            KtToolbar(centerContent = {
                KtText(
                    text = state.title,
                    color = color.textWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            SuggestedProductList(
                items = state.suggestedProductList,
                action = action
            )

            ProductList(
                items = state.productList,
                action = action
            )
        }
    }
}

@Composable
private fun SuggestedProductList(
    items: List<ProductItem>,
    action: ProductListActionHandler
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

            item {
                Spacer(modifier = Modifier.size(8.dp))
            }

            items(
                items = items,
                key = { item -> item.id }
            ) { item ->
                ProductItemCompose(
                    modifier = Modifier.width(100.dp),
                    item = item,
                    onMinusClick = {
                        action.onAction(
                            action = ProductListAction.Remove(
                                id = item.id,
                                source = ProductListAction.Source.Suggested
                            )
                        )
                    },
                    onPlusClick = {
                        action.onAction(
                            action = ProductListAction.Add(
                                id = item.id,
                                source = ProductListAction.Source.Suggested
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductList(
    items: List<ProductItem>,
    action: ProductListActionHandler
) {
    val color = LocalCustomColorsPalette.current

    Box(
        Modifier
            .padding(top = 16.dp)
            .background(color = color.white)
            .padding(top = 8.dp)
    ) {

        LazyVerticalGrid(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = items,
                key = { item -> item.id }
            ) { item ->
                ProductItemCompose(
                    item = item,
                    onMinusClick = {
                        action.onAction(
                            action = ProductListAction.Remove(
                                id = item.id,
                                source = ProductListAction.Source.All
                            )
                        )
                    },
                    onPlusClick = {
                        action.onAction(
                            action = ProductListAction.Add(
                                id = item.id,
                                source = ProductListAction.Source.All
                            )
                        )
                    }
                )
            }
        }
    }
}
