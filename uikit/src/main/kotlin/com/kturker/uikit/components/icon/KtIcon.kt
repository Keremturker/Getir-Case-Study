package com.kturker.uikit.components.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun KtIcon(
    imageVector: ImageVector?,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    tint: Color = Color.Unspecified
) {
    if (imageVector != null) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint
        )
    }
}