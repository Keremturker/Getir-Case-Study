package com.kturker.feature.product.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.text.KtText

@Composable
internal fun BottomCartBar(
    title: String,
    totalPriceFormatted: String,
    onClick: () -> Unit
) {
    val color = LocalCustomColorsPalette.current

    Surface(
        color = color.white,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .weight(2F)
                    .background(
                        color = color.primaryColor,
                        shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                    )
                    .padding(all = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                KtText(
                    text = title,
                    color = color.textWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Box(
                modifier = Modifier
                    .weight(1F)
                    .background(
                        color = color.white,
                        shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(all = 16.dp),
                contentAlignment = Alignment.Center
            ) {

                BasicText(
                    text = AnnotatedString(totalPriceFormatted),
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 14.sp,
                        maxFontSize = 20.sp,
                    ),
                    maxLines = 1,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = color.textPurple
                    )
                )
            }
        }
    }
}