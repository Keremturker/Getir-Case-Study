package com.kturker.navigation

sealed interface NavigationCommand {
    interface Destination : NavigationCommand
    interface Command : NavigationCommand
}

sealed interface ComposeNavigatorCommand : NavigationCommand.Command {

    data object NavigateUp : NavigationCommand.Command

    data class PopBackStackTo(
        val to: NavigationCommand.Destination,
        val inclusive: Boolean = false
    ) : NavigationCommand.Command

}