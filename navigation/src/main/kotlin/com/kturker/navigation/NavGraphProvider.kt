package com.kturker.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey

fun interface NavGraphProvider {
    fun registerGraph(provider: EntryProviderBuilder<NavKey>)
}
