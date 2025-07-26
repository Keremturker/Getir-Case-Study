package com.kturker.feature.cart.presentation.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.core.domain.model.CartItem
import com.kturker.core.domain.model.ProductItem
import com.kturker.core.presentation.BottomCartBar
import com.kturker.core.presentation.ProductItemCompose
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.HorizontalQuantitySelector
import com.kturker.uikit.components.icon.KtIcon
import com.kturker.uikit.components.image.ProductImage
import com.kturker.uikit.components.scaffold.KtScaffold
import com.kturker.uikit.components.text.KtText
import com.kturker.uikit.components.toolbar.KtToolbar
import com.kturker.uikit.extension.noRippleClickable
import com.kturker.uikit.icons.Close
import com.kturker.uikit.icons.Trash

@SuppressLint("DefaultLocale")
@Composable
internal fun CartScreen(uiState: CartUiState, action: CartAction) {
    val color = LocalCustomColorsPalette.current

    KtScaffold(
        topBar = {
            KtToolbar(
                startContent = {
                    KtIcon(
                        imageVector = Close,
                        tint = color.white,
                        modifier = Modifier.noRippleClickable {
                            action.navigateUp()
                        }
                    )
                }, centerContent = {
                    KtText(
                        text = uiState.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = color.textWhite
                    )
                },
                endContent = {
                    KtIcon(
                        imageVector = Trash,
                        modifier = Modifier.noRippleClickable {
                            action.clearCartDialog()
                        },
                        tint = color.white
                    )
                }
            )
        }, bottomBar = {
            BottomCartBar(
                title = uiState.completeOrderButtonTitle,
                totalPriceFormatted = uiState.totalPriceFormatted,
                onClick = action::completeOrderDialog
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                CartList(
                    cartProducts = uiState.cartProducts,
                    suggestedList = uiState.suggestedProducts,
                    suggestedListTitle = uiState.suggestedProductTitle,
                    onPlusClick = action::addToCard,
                    onMinusClick = action::removeFromCard,
                    navigateToDetail = action::navigateToDetail
                )
            }
        }
    )
}

@Composable
private fun CartList(
    suggestedListTitle: String,
    suggestedList: List<ProductItem>,
    cartProducts: List<CartItem>,
    onPlusClick: (id: String) -> Unit,
    onMinusClick: (id: String) -> Unit,
    navigateToDetail: (String) -> Unit
) {
    val color = LocalCustomColorsPalette.current
    LazyColumn(modifier = Modifier.background(color.white)) {
        itemsIndexed(cartProducts, key = { _, item -> item.id }) { index, item ->
            CartItem(
                item = item,
                navigateToDetail = navigateToDetail,
                isLastItem = index == cartProducts.lastIndex,
                onPlusClick = onPlusClick,
                onMinusClick = onMinusClick
            )
        }

        item {
            Column {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(color = color.backgroundColor)
                        .padding(top = 20.dp, bottom = 12.dp)
                        .padding(horizontal = 12.dp)
                ) {
                    KtText(
                        text = suggestedListTitle,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = color.textBlack
                    )
                }
                SuggestedList(
                    suggestedList = suggestedList,
                    onPlusClick = onPlusClick,
                    onMinusClick = onMinusClick,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}

@Composable
private fun CartItem(
    item: CartItem,
    isLastItem: Boolean,
    navigateToDetail: (id: String) -> Unit,
    onPlusClick: (id: String) -> Unit,
    onMinusClick: (id: String) -> Unit
) {
    val color = LocalCustomColorsPalette.current

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductImage(
                modifier = Modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.dp,
                        color = color.softBackground,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .noRippleClickable {
                        navigateToDetail.invoke(item.id)
                    },
                imageUrl = item.imageURL
            )

            Column(Modifier.weight(1F)) {
                KtText(
                    text = item.name,
                    color = color.textBlack,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                KtText(
                    text = item.description,
                    color = color.textGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                KtText(
                    modifier = Modifier.padding(top = 4.dp),
                    text = item.priceText,
                    color = color.textPurple,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            HorizontalQuantitySelector(
                cartCount = item.quantity,
                selectorSize = 32.dp,
                quantityWidth = 38.dp,
                iconSize = 18.dp,
                quantityTextSize = 14.sp,
                onPlusClick = {
                    onPlusClick.invoke(item.id)
                },
                onMinusClick = {
                    onMinusClick.invoke(item.id)
                }
            )
        }
        if (isLastItem.not()) {
            HorizontalDivider(color = color.softBackground)
        }
    }
}

@Composable
private fun SuggestedList(
    suggestedList: List<ProductItem>,
    onPlusClick: (id: String) -> Unit,
    onMinusClick: (id: String) -> Unit,
    navigateToDetail: (id: String) -> Unit
) {

    LazyRow(
        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items = suggestedList, key = { _, item -> item.id }) { index, item ->
            ProductItemCompose(
                modifier = Modifier
                    .padding(start = if (index == 0) 16.dp else 0.dp)
                    .width(100.dp)
                    .noRippleClickable {
                        navigateToDetail.invoke(item.id)
                    },
                item = item,
                onMinusClick = {
                    onMinusClick.invoke(item.id)

                },
                onPlusClick = {
                    onPlusClick.invoke(item.id)
                }
            )
        }
    }
}
