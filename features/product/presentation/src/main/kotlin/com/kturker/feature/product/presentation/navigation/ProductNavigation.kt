package com.kturker.feature.product.presentation.navigation

import com.kturker.core.domain.ProductItem
import com.kturker.feature.cart.contract.CartScreenDestination
import com.kturker.feature.product.contract.ProductDetailArgs
import com.kturker.feature.product.contract.ProductDetailScreenDestination
import com.kturker.navigation.ComposeNavigatorCommand
import com.kturker.navigation.NavigationManager
import javax.inject.Inject

internal class ProductNavigation @Inject constructor(
    private var navigationManager: NavigationManager
) {

    fun navigateUp() {
        navigationManager.navigate(navigationCommand = ComposeNavigatorCommand.NavigateUp)
    }

    fun navigateToDetailScreen(
        item: ProductItem
    ) {
        navigationManager.navigate(
            navigationCommand = ProductDetailScreenDestination(
                args = ProductDetailArgs(
                    id = item.id,
                    imageUrl = item.imageUrl,
                    description = item.description,
                    priceText = item.priceText,
                    name = item.name,
                    price = item.price
                )
            )
        )
    }

    fun navigateToCartScreen() {
        navigationManager.navigate(navigationCommand = CartScreenDestination)
    }
}