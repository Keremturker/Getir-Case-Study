package com.kturker.uikit.components.text

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.kturker.uikit.GetirFontFamily

@Composable
fun KtText(
    modifier: Modifier = Modifier,
    text: String? = null,
    annotatedString: AnnotatedString? = null,
    color: Color = Color.Companion.Unspecified,
    fontSize: TextUnit = TextUnit.Companion.Unspecified,
    fontFamily: FontFamily = GetirFontFamily,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = FontWeight.Companion.Normal,
    letterSpacing: TextUnit = TextUnit.Companion.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Companion.Unspecified,
    overflow: TextOverflow = TextOverflow.Companion.Clip,
    softWrap: Boolean = true,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.Companion.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    val currentText = text?.let { AnnotatedString(text = it) } ?: annotatedString
    ?: AnnotatedString(text = "")

    Text(
        modifier = modifier,
        text = currentText,
        color = color,
        fontFamily = fontFamily,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style
    )
}
