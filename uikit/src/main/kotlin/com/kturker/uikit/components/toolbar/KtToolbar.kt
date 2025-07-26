package com.kturker.uikit.components.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.extension.noRippleClickable

val AppBarHeight = 44.dp
private val ComponentSize = AppBarHeight
private val IconPadding = 8.dp

@Composable
fun KtToolbar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = LocalCustomColorsPalette.current.primaryColor,
    endIconClick: () -> Unit = {},
    startContent: @Composable (() -> Unit)? = null,
    endContent: @Composable (() -> Unit)? = null,
    centerContent: (@Composable () -> Unit)? = null
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(AppBarHeight)
            .background(backgroundColor)
    ) {
        val (startComponent, centerComponent, endComponent) = createRefs()

        Box(
            Modifier
                .size(ComponentSize)
                .padding(all = IconPadding)
                .constrainAs(startComponent) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            startContent?.invoke()
        }

        Box(
            modifier = Modifier
                .constrainAs(
                    centerComponent
                ) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                },
            contentAlignment = Alignment.Center
        ) {
            centerContent?.invoke()
        }

        Box(
            modifier = Modifier
                .constrainAs(endComponent) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .height(ComponentSize)
                .widthIn(min = ComponentSize)
                .padding(horizontal = IconPadding)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .noRippleClickable { endIconClick.invoke() }
            ) {
                endContent?.invoke()
            }
        }
    }
}
