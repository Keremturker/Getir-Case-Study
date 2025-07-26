package com.kturker.feature.product.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.feature.product.presentation.component.AnimatedCartPriceBadge
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.HorizontalQuantitySelector
import com.kturker.uikit.components.icon.KtIcon
import com.kturker.uikit.components.image.ProductImage
import com.kturker.uikit.components.scaffold.KtScaffold
import com.kturker.uikit.components.text.KtText
import com.kturker.uikit.components.toolbar.KtToolbar
import com.kturker.uikit.extension.noRippleClickable
import com.kturker.uikit.icons.Close

@Composable
internal fun ProductDetailScreen(state: ProductDetailUiState, action: ProductDetailAction) {
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
                        })
                },
                centerContent = {
                    KtText(
                        text = state.title,
                        color = color.textWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }, endContent = {
                    AnimatedCartPriceBadge(
                        totalPriceFormatted = state.totalPriceFormatted,
                        modifier = Modifier.noRippleClickable {
                            action.navigateToCartScreen()
                        })
                })
        }, bottomBar = {
            BottomCartBar(
                productQuantity = state.productQuantity,
                title = state.addToCartTitle,
                addToCart = action::addToCart,
                removeFromCart = action::removeFromCart
            )
        },
        containerColor = color.white
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .padding(paddingValues),
            color = color.white,
            shadowElevation = 2.dp
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProductImage(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .defaultMinSize(minWidth = 200.dp, minHeight = 200.dp),
                    imageUrl = state.imageUrl
                )

                KtText(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = state.priceText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = color.textPurple,
                    textAlign = TextAlign.Center
                )

                KtText(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = state.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = color.textBlack,
                    textAlign = TextAlign.Center
                )

                KtText(
                    text = state.description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = color.textGray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun BottomCartBar(
    productQuantity: Int,
    title: String,
    addToCart: () -> Unit,
    removeFromCart: () -> Unit
) {
    val color = LocalCustomColorsPalette.current

    Surface(
        color = color.white,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (productQuantity > 0) {
            HorizontalQuantitySelector(
                modifier = Modifier.padding(all = 16.dp),
                cartCount = productQuantity,
                selectorSize = 48.dp,
                quantityTextSize = 16.sp,
                iconSize = 24.dp,
                onPlusClick = addToCart,
                onMinusClick = removeFromCart
            )
        } else {
            NoProductItem(
                modifier = Modifier.padding(all = 16.dp),
                title = title,
                addToCart = addToCart
            )
        }
    }
}

@Composable
private fun NoProductItem(modifier: Modifier, title: String, addToCart: () -> Unit) {
    val color = LocalCustomColorsPalette.current

    Box(modifier = modifier) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 50.dp),
            onClick = addToCart,
            shape = RoundedCornerShape(size = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color.primaryColor
            )
        ) {
            Text(
                text = title,
                color = color.textWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}