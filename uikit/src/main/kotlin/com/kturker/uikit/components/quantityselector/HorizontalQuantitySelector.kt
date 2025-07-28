package com.kturker.uikit.components.quantityselector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.icon.KtIcon
import com.kturker.uikit.icons.Minus
import com.kturker.uikit.icons.Plus
import com.kturker.uikit.icons.Trash
import kotlinx.coroutines.delay

@Composable
fun HorizontalQuantitySelector(
    quantity: Int,
    selectorSize: Dp,
    iconSize: Dp,
    quantityWidth: Dp = selectorSize,
    quantityTextSize: TextUnit,
    modifier: Modifier = Modifier,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit
) {
    val color = LocalCustomColorsPalette.current

    val removeIcon = if (quantity == 1) Trash else Minus

    var showAnimation by remember { mutableStateOf(false) }
    var previousQuantity by remember { mutableIntStateOf(quantity) }

    LaunchedEffect(key1 = quantity) {
        if (quantity != previousQuantity) {
            showAnimation = true
            delay(300)
            showAnimation = false
            previousQuantity = quantity
        }
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        QuantitySelectActionButton(
            size = selectorSize,
            shape = RoundedCornerShape(bottomStart = 8.dp, topStart = 8.dp),
            onClickAction = {
                onMinusClick.invoke()
            },
            content = {
                KtIcon(
                    imageVector = removeIcon,
                    modifier = Modifier.size(size = iconSize),
                    tint = color.primaryColor
                )
            }
        )

        Box(
            modifier = Modifier
                .size(width = quantityWidth, height = selectorSize)
                .background(color = color.primaryColor),
            contentAlignment = Alignment.Center
        ) {
            if (showAnimation) {
                QuantitySelectorLottie(size = selectorSize, tintColor = color.white)
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
            size = selectorSize,
            shape = RoundedCornerShape(bottomEnd = 8.dp, topEnd = 8.dp),
            onClickAction = {
                onPlusClick.invoke()
            },
            content = {
                KtIcon(
                    imageVector = Plus,
                    modifier = Modifier.size(size = iconSize),
                    tint = color.primaryColor
                )
            }
        )
    }
}
