package com.kturker.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Plus: ImageVector?
    get() {
        if (plus != null) {
            return plus
        }
        plus = ImageVector.Builder(
            name = "Plus",
            defaultWidth = 25.dp,
            defaultHeight = 24.dp,
            viewportWidth = 25f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF5D3EBC)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12.444f, 5f)
                curveTo(12.997f, 5f, 13.444f, 5.448f, 13.444f, 6f)
                verticalLineTo(11f)
                horizontalLineTo(18.444f)
                curveTo(18.997f, 11f, 19.444f, 11.448f, 19.444f, 12f)
                curveTo(19.444f, 12.552f, 18.997f, 13f, 18.444f, 13f)
                horizontalLineTo(13.444f)
                verticalLineTo(18f)
                curveTo(13.444f, 18.552f, 12.997f, 19f, 12.444f, 19f)
                curveTo(11.892f, 19f, 11.444f, 18.552f, 11.444f, 18f)
                verticalLineTo(13f)
                horizontalLineTo(6.444f)
                curveTo(5.892f, 13f, 5.444f, 12.552f, 5.444f, 12f)
                curveTo(5.444f, 11.448f, 5.892f, 11f, 6.444f, 11f)
                lineTo(11.444f, 11f)
                verticalLineTo(6f)
                curveTo(11.444f, 5.448f, 11.892f, 5f, 12.444f, 5f)
                close()
            }
        }.build()

        return plus
    }

private var plus: ImageVector? = null