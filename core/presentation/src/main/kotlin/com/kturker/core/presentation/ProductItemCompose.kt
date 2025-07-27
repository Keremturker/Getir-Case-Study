package com.kturker.core.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.core.domain.model.ProductItem
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.image.ProductImage
import com.kturker.uikit.components.quantityselector.VerticalQuantitySelector
import com.kturker.uikit.components.text.KtText

@Composable
fun ProductItemCompose(
    modifier: Modifier = Modifier,
    item: ProductItem,
    onPlusClick: (id: String) -> Unit,
    onMinusClick: (id: String) -> Unit
) {

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(space = 8.dp)) {
        ProductImageSection(
            id = item.id,
            cartCount = item.cartCount,
            imageUrl = item.imageUrl,
            onPlusClick = onPlusClick,
            onMinusClick = onMinusClick
        )

        ProductInfoSection(
            name = item.name,
            price = item.priceText,
            description = item.description
        )
    }
}

@Composable
private fun ProductImageSection(
    id: String,
    cartCount: Int,
    imageUrl: String,
    onPlusClick: (id: String) -> Unit,
    onMinusClick: (id: String) -> Unit
) {
    val color = LocalCustomColorsPalette.current

    val borderColor = if (cartCount > 0) color.primaryColor else color.softBackground
    val shape = RoundedCornerShape(12.dp)

    Box(Modifier.fillMaxWidth()) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 8.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = shape
                )
                .padding(all = 4.dp)
                .align(Alignment.Center)
                .aspectRatio(1F)
        ) {

            ProductImage(
                imageUrl = imageUrl,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = shape)
            )
        }

        VerticalQuantitySelector(
            modifier = Modifier.align(Alignment.TopEnd),
            quantity = cartCount,
            onPlusClick = {
                onPlusClick.invoke(id)
            },
            onMinusClick = {
                onMinusClick.invoke(id)
            }
        )
    }
}

@Composable
private fun ProductInfoSection(name: String, price: String, description: String) {
    val color = LocalCustomColorsPalette.current

    Column {
        KtText(
            text = price,
            color = color.primaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

        KtText(
            text = name,
            color = color.textBlack,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        KtText(
            modifier = Modifier.padding(top = 2.dp),
            text = description,
            color = color.textGray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
}