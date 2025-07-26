package com.kturker.feature.product.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.icon.KtIcon
import com.kturker.uikit.components.image.ProductImage
import com.kturker.uikit.components.scaffold.KtScaffold
import com.kturker.uikit.components.text.KtText
import com.kturker.uikit.components.toolbar.KtToolbar
import com.kturker.uikit.extension.noRippleClickable
import com.kturker.uikit.icons.Close

@Composable
internal fun ProductDetailScreen(state: ProductDetailUiState, action: ProductDetailAction) {
    val color = LocalCustomColorsPalette.current

    KtScaffold(
        topBar = {
            KtToolbar(
                startContent = {
                    KtIcon(imageVector = Close, modifier = Modifier.noRippleClickable {
                        action.navigateUp()
                    })
                },
                centerContent = {
                    KtText(
                        text = state.title,
                        color = color.textWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                })
        }, bottomBar = {

        },
        containerColor = color.white
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .padding(paddingValues),
            color = color.white,
            shadowElevation = 2.dp
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProductImage(modifier = Modifier.padding(bottom = 16.dp), imageUrl = state.imageUrl)

                KtText(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = state.priceText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = color.textPurple,
                    textAlign = TextAlign.Center
                )

                KtText(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = state.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = color.textBlack,
                    textAlign = TextAlign.Center
                )

                KtText(
                    text = state.description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = color.textGray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

