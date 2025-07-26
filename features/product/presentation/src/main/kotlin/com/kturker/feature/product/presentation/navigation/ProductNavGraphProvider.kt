package com.kturker.feature.product.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import com.kturker.core.presentation.CustomNavType
import com.kturker.core.presentation.ktAnimatedDestination
import com.kturker.feature.product.contract.ProductDetailArgs
import com.kturker.feature.product.contract.ProductDetailScreenDestination
import com.kturker.feature.product.contract.ProductListScreenDestination
import com.kturker.feature.product.presentation.detail.ProductDetailRouteScreen
import com.kturker.feature.product.presentation.list.ProductListRouteScreen
import com.kturker.navigation.NavGraphProvider
import javax.inject.Inject
import kotlin.reflect.typeOf

internal class ProductNavGraphProvider @Inject constructor() : NavGraphProvider {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            ktAnimatedDestination<ProductListScreenDestination>(
                enterTransition = { slideInHorizontally { fullWidth -> fullWidth } + fadeIn() },

                ) {
                ProductListRouteScreen()
            }

            ktAnimatedDestination<ProductDetailScreenDestination>(
                typeMap = mapOf(
                    typeOf<ProductDetailArgs>() to CustomNavType(ProductDetailArgs.serializer())
                )

            ) {
                ProductDetailRouteScreen()
            }
        }
    }
}
