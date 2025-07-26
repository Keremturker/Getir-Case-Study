package com.kturker.feature.product.contract

import com.kturker.navigation.NavigationCommand
import kotlinx.serialization.Serializable

@Serializable
data object ProductListScreenDestination : NavigationCommand.Destination

@Serializable
data class ProductDetailScreenDestination(
    val args: ProductDetailArgs
) : NavigationCommand.Destination


@Serializable
data class ProductDetailArgs(
    val id: String,
    val imageUrl: String,
    val description: String,
    val priceText: String,
    val name: String
)
