package com.kturker.feature.product.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.icon.KtIcon
import com.kturker.uikit.components.text.KtText
import com.kturker.uikit.extension.noRippleClickable
import com.kturker.uikit.icons.Bucket
import kotlinx.coroutines.delay

@Composable
internal fun CartPriceBadge(
    priceText: String,
    onClick: () -> Unit = {}
) {
    val color = LocalCustomColorsPalette.current

    var visiblePrice by remember { mutableStateOf(priceText) }

    LaunchedEffect(key1 = priceText) {
        if (priceText.isNotEmpty()) {
            visiblePrice = ""
            delay(120)
            visiblePrice = priceText
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = color.white,
                shape = RoundedCornerShape(8.dp)
            )
            .height(34.dp)
            .background(color = color.white, shape = RoundedCornerShape(8.dp))
            .noRippleClickable {
                onClick.invoke()
            }
    ) {

        Box(
            modifier = Modifier
                .background(color = color.white, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp)
        ) {
            KtIcon(
                imageVector = Bucket,
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    color = color.softBackground,
                    shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                )
                .padding(horizontal = 10.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 240,
                        easing = FastOutSlowInEasing
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            KtText(
                text = visiblePrice,
                color = color.textPurple,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
