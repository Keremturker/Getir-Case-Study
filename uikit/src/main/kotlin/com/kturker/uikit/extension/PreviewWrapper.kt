package com.kturker.uikit.extension

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.FOLDABLE
import androidx.compose.ui.tooling.preview.Devices.NEXUS_5
import androidx.compose.ui.tooling.preview.Devices.PHONE
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.OnDarkCustomColorsPalette
import com.kturker.uikit.OnLightCustomColorsPalette

@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Preview(name = "Phone", device = PHONE)
@Preview(name = "Small Phone", device = NEXUS_5)
@Preview(name = "Unfolded Foldable", device = FOLDABLE)
@Preview(name = "Tablet", device = TABLET)
annotation class PreviewGetir

@Composable
fun KtPreviewWrapper(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val palette = if (isDarkTheme) OnDarkCustomColorsPalette else OnLightCustomColorsPalette

    CompositionLocalProvider(value = LocalCustomColorsPalette provides palette) {
        MaterialTheme {
            Surface(
                modifier = Modifier.background(palette.backgroundColor)
            ) {
                content()
            }
        }
    }
}