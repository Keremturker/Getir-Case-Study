package com.kturker.uikit.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kturker.uikit.R

@Composable
fun ProductImage(imageUrl: String, modifier: Modifier = Modifier) {
    KtAsyncImage(
        modifier = modifier,
        image = imageUrl,
        error = painterResource(R.drawable.placeholder),
        placeholder = painterResource(R.drawable.placeholder)
    )
}
