package com.kturker.uikit

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Suppress("FunctionParameterArgumentRule")
val GetirFontFamily = FontFamily(
    Font(R.font.open_sans_light, weight = FontWeight.Companion.Light),
    Font(R.font.open_sans_regular, weight = FontWeight.Companion.Normal),
    Font(R.font.open_sans_semi_bold, weight = FontWeight.Companion.SemiBold),
    Font(R.font.open_sans_bold, weight = FontWeight.Companion.Bold)
)
