package com.kturker.uikit.extension

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.OnDarkCustomColorsPalette
import com.kturker.uikit.OnLightCustomColorsPalette

@Composable
fun KtPreviewWrapper(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val palette = if (isDarkTheme) OnDarkCustomColorsPalette else OnLightCustomColorsPalette

    CompositionLocalProvider(value = LocalCustomColorsPalette provides palette) {
        MaterialTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(palette.backgroundColor)
            ) {
                content()
            }
        }
    }
}