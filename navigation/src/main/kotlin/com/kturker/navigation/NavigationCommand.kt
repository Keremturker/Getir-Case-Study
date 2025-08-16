package com.kturker.navigation

import androidx.navigation3.runtime.NavKey

sealed interface NavigationCommand {
    interface Destination : NavigationCommand, NavKey
    interface Command : NavigationCommand, NavKey
}

sealed interface ComposeNavigatorCommand : NavigationCommand.Command {

    data object NavigateUp : NavigationCommand.Command

    data class PopBackStackTo(
        val to: NavigationCommand.Destination
    ) : NavigationCommand.Command
}
