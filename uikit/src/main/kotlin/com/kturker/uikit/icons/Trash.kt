@file:Suppress("MagicNumber")

package com.kturker.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Trash: ImageVector?
    get() {
        if (trash != null) {
            return trash
        }
        trash = ImageVector.Builder(
            name = "Trash",
            defaultWidth = 25.dp,
            defaultHeight = 24.dp,
            viewportWidth = 25f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF5D3EBC))) {
                moveTo(20.644f, 6f)
                horizontalLineTo(15.844f)
                curveTo(15.844f, 4.343f, 14.501f, 3f, 12.844f, 3f)
                curveTo(11.187f, 3f, 9.844f, 4.343f, 9.844f, 6f)
                horizontalLineTo(5.044f)
                curveTo(4.713f, 6f, 4.444f, 6.269f, 4.444f, 6.6f)
                verticalLineTo(8.4f)
                curveTo(4.444f, 8.731f, 4.713f, 9f, 5.044f, 9f)
                horizontalLineTo(5.644f)
                lineTo(7.186f, 19.716f)
                curveTo(7.289f, 20.445f, 7.908f, 20.99f, 8.644f, 21f)
                horizontalLineTo(16.948f)
                curveTo(17.691f, 20.998f, 18.32f, 20.451f, 18.424f, 19.716f)
                lineTo(19.96f, 9f)
                horizontalLineTo(20.644f)
                curveTo(20.975f, 9f, 21.244f, 8.731f, 21.244f, 8.4f)
                verticalLineTo(6.6f)
                curveTo(21.244f, 6.269f, 20.975f, 6f, 20.644f, 6f)
                close()
                moveTo(10.78f, 18.042f)
                horizontalLineTo(10.708f)
                curveTo(10.232f, 18.073f, 9.819f, 17.714f, 9.784f, 17.238f)
                lineTo(9.4f, 11.778f)
                curveTo(9.369f, 11.302f, 9.728f, 10.889f, 10.204f, 10.854f)
                horizontalLineTo(10.276f)
                curveTo(10.752f, 10.823f, 11.165f, 11.182f, 11.2f, 11.658f)
                lineTo(11.584f, 17.118f)
                curveTo(11.615f, 17.594f, 11.256f, 18.007f, 10.78f, 18.042f)
                close()
                moveTo(15.898f, 17.244f)
                curveTo(15.863f, 17.72f, 15.45f, 18.079f, 14.974f, 18.048f)
                horizontalLineTo(14.902f)
                curveTo(14.426f, 18.013f, 14.067f, 17.6f, 14.098f, 17.124f)
                lineTo(14.482f, 11.664f)
                curveTo(14.517f, 11.188f, 14.93f, 10.829f, 15.406f, 10.86f)
                horizontalLineTo(15.478f)
                curveTo(15.954f, 10.895f, 16.313f, 11.308f, 16.282f, 11.784f)
                lineTo(15.898f, 17.244f)
                close()
                moveTo(12.844f, 4.8f)
                curveTo(13.507f, 4.8f, 14.044f, 5.337f, 14.044f, 6f)
                horizontalLineTo(11.644f)
                curveTo(11.644f, 5.337f, 12.181f, 4.8f, 12.844f, 4.8f)
                close()
            }
        }.build()

        return trash
    }

private var trash: ImageVector? = null
