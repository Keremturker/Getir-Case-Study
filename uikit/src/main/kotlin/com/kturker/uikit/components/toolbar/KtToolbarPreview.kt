package com.kturker.uikit.components.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.OnDarkCustomColorsPalette
import com.kturker.uikit.components.text.KtText

@Preview
@Composable
private fun KtToolbarPreview() {
    CompositionLocalProvider(LocalCustomColorsPalette provides OnDarkCustomColorsPalette) {
        KtToolbar(
            centerContent = {
                KtText(
                    text = "ToolbarTitle",
                    fontWeight = FontWeight.Bold,
                    color = LocalCustomColorsPalette.current.textWhite
                )
            }
        )
    }
}
