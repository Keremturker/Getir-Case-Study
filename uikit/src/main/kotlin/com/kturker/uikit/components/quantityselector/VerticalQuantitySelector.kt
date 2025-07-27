package com.kturker.uikit.components.quantityselector

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.icon.KtIcon
import com.kturker.uikit.icons.Minus
import com.kturker.uikit.icons.Plus
import com.kturker.uikit.icons.Trash
import kotlinx.coroutines.delay

@Composable
fun VerticalQuantitySelector(
    quantity: Int,
    selectorWidth: Dp = 32.dp,
    iconSize: Dp = 24.dp,
    quantityTextSize: TextUnit = 12.sp,
    modifier: Modifier = Modifier,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit
) {
    val color = LocalCustomColorsPalette.current

    val isExpanded = quantity > 0
    val removeIcon = if (quantity == 1) Trash else Minus

    var showAnimation by remember { mutableStateOf(false) }
    var isClickedMinus by remember { mutableStateOf(false) }
    var previousQuantity by remember { mutableIntStateOf(quantity) }

    LaunchedEffect(key1 = quantity) {
        if (quantity != previousQuantity) {
            showAnimation = true
            delay(300)
            showAnimation = false
            previousQuantity = quantity
        }
    }

    val animatedHeight by animateDpAsState(
        targetValue = if (isExpanded) 96.dp else 32.dp,
        label = "heightAnimation",
        animationSpec = tween(
            durationMillis = 400
        )
    )

    Column(
        modifier = modifier
            .height(animatedHeight)
            .width(selectorWidth),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        QuantitySelectActionButton(
            size = selectorWidth,
            onClickAction = {
                isClickedMinus = false
                onPlusClick.invoke()
            },
            shape = if (isExpanded) {
                RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            } else {
                RoundedCornerShape(8.dp)
            },
            content = {
                if (showAnimation && quantity == 1 && isClickedMinus.not() ) {
                    QuantitySelectorLottie(size = selectorWidth, tintColor = color.primaryColor)
                } else {
                    KtIcon(
                        imageVector = Plus,
                        modifier = Modifier.size(iconSize),
                        tint = color.primaryColor
                    )
                }
            }
        )

        if (isExpanded) {
            Box(
                modifier = Modifier
                    .size(size = selectorWidth)
                    .background(color = color.primaryColor),
                contentAlignment = Alignment.Center
            ) {

                if (showAnimation && quantity != 1 || (showAnimation && isClickedMinus)) {
                    QuantitySelectorLottie(size = selectorWidth, tintColor = color.white)
                } else {
                    Text(
                        text = quantity.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = quantityTextSize,
                        color = color.textWhite
                    )
                }
            }

            QuantitySelectActionButton(
                size = selectorWidth,
                content = {
                    KtIcon(
                        imageVector = removeIcon,
                        modifier = Modifier.size(iconSize),
                        tint = color.primaryColor
                    )
                },
                onClickAction = {
                    isClickedMinus = true
                    onMinusClick.invoke()
                },
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
        }
    }
}

