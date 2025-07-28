package com.kturker.uikit.components.quantityselector

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kturker.uikit.R
import com.kturker.uikit.components.KtLottie
import com.kturker.uikit.extension.noRippleClickable

@Composable
internal fun QuantitySelectActionButton(
    size: Dp,
    shape: Shape,
    onClickAction: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .size(size)
            .noRippleClickable {
                onClickAction.invoke()
            },
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            content.invoke()
        }
    }
}

@Composable
internal fun QuantitySelectorLottie(size: Dp, tintColor: Color? = null) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loading_animation)
    )

    KtLottie(
        lottieComposition = composition,
        modifier = Modifier
            .size(size = size)
            .padding(4.dp),
        tintColor = tintColor
    )
}
