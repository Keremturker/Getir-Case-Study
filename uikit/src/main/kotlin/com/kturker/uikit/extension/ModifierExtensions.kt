package com.kturker.uikit.extension

import android.annotation.SuppressLint
import android.os.SystemClock
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.kturker.uikit.LocalCustomColorsPalette

private const val SHIMMER_ANIM_DURATION = 1_000
private const val SHIMMER_MULTIPLIER_VALUE_NEGATIVE = -2
private const val SHIMMER_MULTIPLIER_VALUE_POSITIVE = 2

@SuppressLint("SuspiciousModifierThen")
fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    debounceTime: Long = 600L,
    onClick: () -> Unit
): Modifier = composed {
    var lastClickTime: Long = 0
    this.then(
        clickable(
            enabled = enabled,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                return@clickable
            } else {
                onClick()
            }
            lastClickTime = SystemClock.elapsedRealtime()
        }
    )
}

fun Modifier.shimmerEffect(): Modifier = composed {
    val color = LocalCustomColorsPalette.current

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "shimmerEffect")
    val startOffsetX by transition.animateFloat(
        initialValue = SHIMMER_MULTIPLIER_VALUE_NEGATIVE * size.width.toFloat(),
        targetValue = SHIMMER_MULTIPLIER_VALUE_POSITIVE * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(SHIMMER_ANIM_DURATION)
        ),
        label = "shimmerEffect"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                color.shimmerMain,
                color.shimmerReflect,
                color.shimmerMain
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}