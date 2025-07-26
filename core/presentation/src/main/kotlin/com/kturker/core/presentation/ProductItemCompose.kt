package com.kturker.core.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kturker.core.domain.model.ProductItem
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.components.icon.KtIcon
import com.kturker.uikit.components.image.ProductImage
import com.kturker.uikit.components.text.KtText
import com.kturker.uikit.extension.noRippleClickable
import com.kturker.uikit.icons.Minus
import com.kturker.uikit.icons.Plus
import com.kturker.uikit.icons.Trash

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

        QuantitySelector(
            modifier = Modifier.align(Alignment.TopEnd),
            cartCount = cartCount,
            itemId = id,
            onPlusClick = onPlusClick,
            onMinusClick = onMinusClick
        )
    }
}

@Composable
private fun QuantitySelector(
    itemId: String,
    cartCount: Int,
    modifier: Modifier = Modifier,
    onPlusClick: (id: String) -> Unit,
    onMinusClick: (id: String) -> Unit
) {
    val color = LocalCustomColorsPalette.current

    val isExpanded = cartCount > 0
    val removeIcon = if (cartCount == 1) Trash else Minus

    val animatedHeight by animateDpAsState(
        targetValue = if (isExpanded) 96.dp else 32.dp,
        label = "heightAnimation",
        animationSpec = tween(
            durationMillis = 400
        )
    )

    Column(
        modifier = modifier
            .height(animatedHeight)
            .width(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ActionButton(
            imageVector = Plus,
            onClickAction = {
                onPlusClick.invoke(itemId)
            },
            shape = if (isExpanded) {
                RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            } else {
                RoundedCornerShape(8.dp)
            }
        )

        if (isExpanded) {
            Box(
                modifier = Modifier
                    .size(size = 32.dp)
                    .background(
                        color = color.primaryColor
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = cartCount.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = color.textWhite
                )
            }

            ActionButton(
                imageVector = removeIcon,
                onClickAction = {
                    onMinusClick.invoke(itemId)
                },
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
        }
    }
}

@Composable
private fun ActionButton(imageVector: ImageVector?, onClickAction: () -> Unit, shape: Shape) {
    val color = LocalCustomColorsPalette.current

    Card(
        modifier = Modifier
            .size(32.dp)
            .noRippleClickable(debounceTime = 0L) {
                onClickAction.invoke()
            },
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            KtIcon(
                imageVector = imageVector,
                contentDescription = "Plus",
                modifier = Modifier.size(24.dp),
                tint = color.primaryColor
            )
        }
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