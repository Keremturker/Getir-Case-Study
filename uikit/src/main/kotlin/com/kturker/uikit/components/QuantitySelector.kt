package com.kturker.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.icon.KtIcon
import com.kturker.uikit.extension.noRippleClickable
import com.kturker.uikit.icons.Minus
import com.kturker.uikit.icons.Plus
import com.kturker.uikit.icons.Trash

@Composable
fun HorizontalQuantitySelector(
    cartCount: Int,
    selectorSize: Dp,
    iconSize: Dp,
    quantityWidth: Dp = selectorSize,
    quantityTextSize: TextUnit,
    modifier: Modifier = Modifier,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit
) {
    val color = LocalCustomColorsPalette.current

    val removeIcon = if (cartCount == 1) Trash else Minus

    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        ActionButton(
            size = selectorSize,
            imageVector = removeIcon,
            onClickAction = {
                onMinusClick.invoke()
            },
            shape = RoundedCornerShape(bottomStart = 8.dp, topStart = 8.dp),
            iconSize = iconSize
        )

        Box(
            modifier = Modifier
                .size(width = quantityWidth, height = selectorSize)
                .background(color = color.primaryColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = cartCount.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = quantityTextSize,
                color = color.textWhite
            )
        }

        ActionButton(
            size = selectorSize,
            imageVector = Plus,
            onClickAction = {
                onPlusClick.invoke()
            },
            shape = RoundedCornerShape(bottomEnd = 8.dp, topEnd = 8.dp),
            iconSize = iconSize
        )
    }
}

@Composable
private fun ActionButton(
    size: Dp,
    iconSize: Dp,
    imageVector: ImageVector?,
    onClickAction: () -> Unit,
    shape: Shape
) {
    val color = LocalCustomColorsPalette.current

    Card(
        modifier = Modifier
            .size(size)
            .noRippleClickable(debounceTime = 0L) {
                onClickAction.invoke()
            },
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            KtIcon(
                imageVector = imageVector,
                modifier = Modifier.size(size = iconSize),
                tint = color.primaryColor
            )
        }
    }
}