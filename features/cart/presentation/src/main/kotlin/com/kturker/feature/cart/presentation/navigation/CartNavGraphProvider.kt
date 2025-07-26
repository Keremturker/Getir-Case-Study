package com.kturker.feature.cart.presentation.navigation

import androidx.navigation.NavGraphBuilder
import com.kturker.core.presentation.ktAnimatedDestination
import com.kturker.feature.cart.contract.CartScreenDestination
import com.kturker.feature.cart.presentation.cart.CartRoutScreen
import com.kturker.navigation.NavGraphProvider
import javax.inject.Inject

internal class CartNavGraphProvider @Inject constructor() : NavGraphProvider {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            ktAnimatedDestination<CartScreenDestination> {
                CartRoutScreen()
            }
        }
    }
}
