package com.kturker.feature.cart.presentation.navigation

import com.kturker.core.domain.model.ProductItem
import com.kturker.feature.product.contract.ProductDetailArgs
import com.kturker.feature.product.contract.ProductDetailScreenDestination
import com.kturker.navigation.ComposeNavigatorCommand
import com.kturker.navigation.NavigationManager
import javax.inject.Inject

internal class CartNavigation @Inject constructor(
    private var navigationManager: NavigationManager
) {

    fun navigateUp() {
        navigationManager.navigate(navigationCommand = ComposeNavigatorCommand.NavigateUp)
    }

    fun popBackStack() {
        navigationManager.navigate(navigationCommand = ComposeNavigatorCommand.PopBackStack)
    }

    fun navigateToDetail(productItem: ProductItem) {
        val args = ProductDetailArgs(
            id = productItem.id,
            imageUrl = productItem.imageUrl,
            description = productItem.description,
            priceText = productItem.priceText,
            name = productItem.name,
            price = productItem.price,
            isCameFromCart = true
        )
        navigationManager.navigate(navigationCommand = ProductDetailScreenDestination(args))
    }
}