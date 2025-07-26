package com.kturker.feature.product.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kturker.feature.product.contract.ProductListScreenDestination
import com.kturker.feature.product.presentation.list.ProductListRouteScreen
import com.kturker.navigation.NavGraphProvider
import javax.inject.Inject

internal class ProductNavGraphProvider @Inject constructor() : NavGraphProvider {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            composable<ProductListScreenDestination> {
                ProductListRouteScreen()
            }
        }
    }
}
