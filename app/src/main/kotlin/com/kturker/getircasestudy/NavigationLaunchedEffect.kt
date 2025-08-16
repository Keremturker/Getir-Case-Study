package com.kturker.getircasestudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.NavBackStack
import com.kturker.navigation.ComposeNavigatorCommand
import com.kturker.navigation.NavigationCommand
import com.kturker.navigation.NavigationManager

@Composable
internal fun NavigationLaunchedEffect(
    navigationManager: NavigationManager,
    backStack: NavBackStack
) {
    LaunchedEffect(key1 = Unit) {
        navigationManager.navigationCommandFlow.collect {
            when (it) {
                is NavigationCommand.Destination -> {
                    backStack.add(it)
                }

                is NavigationCommand.Command -> {
                    when (it) {
                        ComposeNavigatorCommand.NavigateUp -> {
                            backStack.removeLastOrNull()
                        }

                        is ComposeNavigatorCommand.PopBackStackTo -> {
                            val index = backStack.indexOf(it.to)
                            if (index != -1 && index < backStack.lastIndex) {
                                backStack.subList(index + 1, backStack.size).clear()
                            }
                        }
                    }
                }
            }
        }
    }
}
