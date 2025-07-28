package com.kturker.uikit

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorsPalette(
    val backgroundColor: Color = Color.Unspecified,
    val softBackground: Color = Color.Unspecified,
    val primaryColor: Color = Color(color = 0xFF5D3EBC),
    val white: Color = Color.White,
    val textWhite: Color = Color.Unspecified,
    val textPurple: Color = primaryColor,
    val textBlack: Color = Color.Unspecified,
    val textGray: Color = Color.Unspecified,
    val shimmerMain: Color = Color.Unspecified,
    val shimmerReflect: Color = Color.Unspecified
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightCustomColorsPalette = CustomColorsPalette(
    backgroundColor = Color(color = 0xFFF5F5F5),
    softBackground = Color(color = 0xFFF2F0FA),
    textWhite = Color(color = 0xFFFFFFFF),
    textBlack = Color(color = 0xFF191919),
    textGray = Color(color = 0xFF697488),
    shimmerMain = Color(color = 0xFFEAEAEA),
    shimmerReflect = Color(color = 0xFFBCBCBC)
)

val OnDarkCustomColorsPalette = CustomColorsPalette(
    backgroundColor = Color(color = 0xFFF5F5F5),
    softBackground = Color(color = 0xFFF2F0FA),
    textWhite = Color(color = 0xFFFFFFFF),
    textBlack = Color(color = 0xFF191919),
    textGray = Color(color = 0xFF697488),
    shimmerMain = Color(color = 0xFFEAEAEA),
    shimmerReflect = Color(color = 0xFFBCBCBC)
)
