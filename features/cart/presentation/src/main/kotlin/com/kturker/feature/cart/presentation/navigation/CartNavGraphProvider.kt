package com.kturker.feature.cart.presentation.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.kturker.feature.cart.contract.CartScreenDestination
import com.kturker.feature.cart.presentation.cart.CartRoutScreen
import com.kturker.navigation.NavGraphProvider
import javax.inject.Inject

internal class CartNavGraphProvider @Inject constructor() : NavGraphProvider {
    override fun registerGraph(provider: EntryProviderBuilder<NavKey>) {
        provider.entry<CartScreenDestination> {
            CartRoutScreen()
        }
    }
}
