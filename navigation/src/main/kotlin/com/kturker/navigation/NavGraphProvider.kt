package com.kturker.navigation

import androidx.navigation.NavGraphBuilder

fun interface NavGraphProvider {
    fun registerGraph(navGraphBuilder: NavGraphBuilder)
}
