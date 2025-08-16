package com.kturker.feature.product.presentation.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.kturker.feature.product.contract.ProductDetailScreenDestination
import com.kturker.feature.product.contract.ProductListScreenDestination
import com.kturker.feature.product.presentation.detail.ProductDetailRouteScreen
import com.kturker.feature.product.presentation.list.ProductListRouteScreen
import com.kturker.navigation.NavGraphProvider
import javax.inject.Inject

internal class ProductNavGraphProvider @Inject constructor() : NavGraphProvider {
    override fun registerGraph(provider: EntryProviderBuilder<NavKey>) {
        provider.entry<ProductListScreenDestination> {
            ProductListRouteScreen()
        }

        provider.entry<ProductDetailScreenDestination> { key ->
            ProductDetailRouteScreen(args = key.args)
        }
    }
}
