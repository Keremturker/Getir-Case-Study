package com.kturker.uikit

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorsPalette(
    val backgroundColor: Color = Color.Unspecified,
    val primaryColor: Color = Color.Unspecified
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightCustomColorsPalette = CustomColorsPalette(
    backgroundColor = Color(color = 0xFFF5F5F5),
    primaryColor = Color(color = 0xFF5D3EBC)
)

val OnDarkCustomColorsPalette = CustomColorsPalette(
    backgroundColor = Color(color = 0xFFF5F5F5),
    primaryColor = Color(color = 0xFF5D3EBC)
)
