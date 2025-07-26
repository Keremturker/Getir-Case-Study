package com.kturker.uikit.components.image

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kturker.uikit.R

@Composable
fun ProductImage(imageUrl: String, modifier: Modifier = Modifier) {

    KtAsyncImage(
        modifier = modifier.defaultMinSize(minWidth = 200.dp, minHeight = 200.dp),
        image = imageUrl,
        error = painterResource(R.drawable.placeholder),
        placeholder = painterResource(R.drawable.placeholder)
    )
}