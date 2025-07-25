package com.kturker.uikit.components.extension

import android.annotation.SuppressLint
import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

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
