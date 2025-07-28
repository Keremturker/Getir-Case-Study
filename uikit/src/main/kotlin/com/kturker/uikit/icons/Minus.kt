@file:Suppress("MagicNumber")

package com.kturker.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Minus: ImageVector?
    get() {
        if (minus != null) {
            return minus
        }
        minus = ImageVector.Builder(
            name = "Minus",
            defaultWidth = 25.dp,
            defaultHeight = 24.dp,
            viewportWidth = 25f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF5D3EBC)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5.278f, 12f)
                curveTo(5.278f, 11.448f, 5.726f, 11f, 6.278f, 11f)
                lineTo(18.278f, 11f)
                curveTo(18.83f, 11f, 19.278f, 11.448f, 19.278f, 12f)
                curveTo(19.278f, 12.552f, 18.83f, 13f, 18.278f, 13f)
                lineTo(6.278f, 13f)
                curveTo(5.726f, 13f, 5.278f, 12.552f, 5.278f, 12f)
                close()
            }
        }.build()

        return minus
    }

private var minus: ImageVector? = null
