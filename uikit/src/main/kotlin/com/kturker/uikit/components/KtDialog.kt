package com.kturker.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.text.KtText

@Composable
fun KtBasicDialog(
    onDismissRequest: () -> Unit,
    description: String,
    positiveButtonTitle: String,
    negativeButtonTitle: String?,
    positiveButtonAction: () -> Unit,
    negativeButtonAction: () -> Unit
) {
    val color = LocalCustomColorsPalette.current

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .background(
                    color = color.white,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp)
                .padding(
                    bottom = 8.dp, top = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            KtText(
                color = color.textBlack,
                text = description,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                negativeButtonTitle?.let {
                    DialogButton(
                        modifier = Modifier.weight(weight = 1F),
                        isPrimaryButton = false,
                        title = negativeButtonTitle,
                        clickAction = negativeButtonAction
                    )
                }

                DialogButton(
                    modifier = Modifier.weight(weight = 1F),
                    isPrimaryButton = true,
                    title = positiveButtonTitle,
                    clickAction = positiveButtonAction
                )
            }
        }
    }
}

@Composable
private fun DialogButton(
    modifier: Modifier,
    isPrimaryButton: Boolean = false,
    title: String,
    clickAction: () -> Unit
) {
    val color = LocalCustomColorsPalette.current
    val shape = RoundedCornerShape(8.dp)

    Box(
        modifier = modifier
            .border(1.dp, color = color.primaryColor, shape = shape)
            .background(
                color = if (isPrimaryButton) color.primaryColor else color.white,
                shape = shape
            )
            .clip(shape = shape)
            .clickable {
                clickAction.invoke()
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = if (isPrimaryButton) color.textWhite else color.textPurple,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }

}