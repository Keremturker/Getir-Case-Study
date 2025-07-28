@file:Suppress("MagicNumber")

package com.kturker.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Bucket: ImageVector?
    get() {
        if (bucket != null) {
            return bucket
        }
        bucket = ImageVector.Builder(
            name = "Bucket",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF5D3EBC)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(18.807f, 8.7f)
                curveTo(18.667f, 8.555f, 18.469f, 8.474f, 18.262f, 8.474f)
                horizontalLineTo(18.261f)
                lineTo(5.736f, 8.499f)
                curveTo(5.529f, 8.499f, 5.331f, 8.582f, 5.192f, 8.727f)
                curveTo(5.052f, 8.872f, 4.984f, 9.066f, 5.003f, 9.261f)
                lineTo(5.94f, 18.455f)
                curveTo(5.963f, 19.664f, 7.075f, 21f, 8.558f, 21f)
                horizontalLineTo(15.452f)
                curveTo(16.858f, 21f, 18.048f, 19.924f, 18.07f, 18.643f)
                lineTo(18.997f, 9.233f)
                curveTo(19.016f, 9.038f, 18.947f, 8.844f, 18.807f, 8.7f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFD10D)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(14.1f, 10.499f)
                verticalLineTo(7.527f)
                curveTo(14.1f, 6.288f, 13.178f, 5.318f, 12f, 5.318f)
                curveTo(10.822f, 5.318f, 9.9f, 6.288f, 9.9f, 7.527f)
                verticalLineTo(10.499f)
                curveTo(9.9f, 10.863f, 9.587f, 11.158f, 9.2f, 11.158f)
                curveTo(8.813f, 11.158f, 8.5f, 10.863f, 8.5f, 10.499f)
                verticalLineTo(7.527f)
                curveTo(8.5f, 5.549f, 10.036f, 4f, 12f, 4f)
                curveTo(13.964f, 4f, 15.5f, 5.549f, 15.5f, 7.527f)
                verticalLineTo(10.499f)
                curveTo(15.5f, 10.863f, 15.187f, 11.158f, 14.8f, 11.158f)
                curveTo(14.413f, 11.158f, 14.1f, 10.863f, 14.1f, 10.499f)
                close()
            }
        }.build()

        return bucket
    }

private var bucket: ImageVector? = null
