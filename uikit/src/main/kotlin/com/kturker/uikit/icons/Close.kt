package com.kturker.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Close: ImageVector?
    get() {
        if (close != null) {
            return close
        }
        close = ImageVector.Builder(
            name = "Close",
            defaultWidth = 12.dp,
            defaultHeight = 12.dp,
            viewportWidth = 12f,
            viewportHeight = 12f
        ).apply {
            path(
                fill = SolidColor(Color.White),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(0.287f, 0.305f)
                curveTo(0.474f, 0.118f, 0.729f, 0.013f, 0.994f, 0.013f)
                curveTo(1.259f, 0.013f, 1.513f, 0.118f, 1.701f, 0.305f)
                lineTo(5.994f, 4.598f)
                lineTo(10.287f, 0.305f)
                curveTo(10.379f, 0.21f, 10.489f, 0.134f, 10.611f, 0.081f)
                curveTo(10.733f, 0.029f, 10.865f, 0.001f, 10.997f, 0f)
                curveTo(11.13f, -0.001f, 11.262f, 0.024f, 11.385f, 0.074f)
                curveTo(11.508f, 0.125f, 11.619f, 0.199f, 11.713f, 0.293f)
                curveTo(11.807f, 0.387f, 11.881f, 0.498f, 11.932f, 0.621f)
                curveTo(11.982f, 0.744f, 12.007f, 0.876f, 12.006f, 1.009f)
                curveTo(12.005f, 1.141f, 11.977f, 1.273f, 11.925f, 1.395f)
                curveTo(11.872f, 1.517f, 11.796f, 1.627f, 11.701f, 1.719f)
                lineTo(7.408f, 6.012f)
                lineTo(11.701f, 10.305f)
                curveTo(11.883f, 10.494f, 11.984f, 10.747f, 11.981f, 11.009f)
                curveTo(11.979f, 11.271f, 11.874f, 11.522f, 11.689f, 11.707f)
                curveTo(11.503f, 11.892f, 11.252f, 11.998f, 10.99f, 12f)
                curveTo(10.728f, 12.002f, 10.475f, 11.901f, 10.287f, 11.719f)
                lineTo(5.994f, 7.426f)
                lineTo(1.701f, 11.719f)
                curveTo(1.512f, 11.901f, 1.26f, 12.002f, 0.997f, 12f)
                curveTo(0.735f, 11.998f, 0.484f, 11.892f, 0.299f, 11.707f)
                curveTo(0.114f, 11.522f, 0.008f, 11.271f, 0.006f, 11.009f)
                curveTo(0.004f, 10.747f, 0.105f, 10.494f, 0.287f, 10.305f)
                lineTo(4.58f, 6.012f)
                lineTo(0.287f, 1.719f)
                curveTo(0.099f, 1.532f, -0.006f, 1.277f, -0.006f, 1.012f)
                curveTo(-0.006f, 0.747f, 0.099f, 0.493f, 0.287f, 0.305f)
                close()
            }
        }.build()

        return close
    }

private var close: ImageVector? = null