package com.kturker.navigation

sealed interface NavigationCommand {

    data class OpenScreen(
        val route: String,
        val clearBackStack: Boolean = false,
        val addToBackStack: Boolean = true
    ) : NavigationCommand

    data class PopBackStackTo(val route: String, val inclusive: Boolean = false) : NavigationCommand

    data object NavigateUp : NavigationCommand

    data object PopBackStack : NavigationCommand

}