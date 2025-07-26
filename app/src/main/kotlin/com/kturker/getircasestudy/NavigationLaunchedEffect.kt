package com.kturker.getircasestudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.kturker.navigation.ComposeNavigatorCommand
import com.kturker.navigation.NavigationCommand
import com.kturker.navigation.NavigationManager

@Composable
internal fun NavigationLaunchedEffect(
    navigationManager: NavigationManager,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        navigationManager.navigationCommandFlow.collect {
            when (it) {
                is NavigationCommand.Destination -> {
                    navController.navigate(route = it)
                }

                is NavigationCommand.Command -> {
                    when (it) {
                        ComposeNavigatorCommand.NavigateUp -> {
                            navController.navigateUp()
                        }

                        is ComposeNavigatorCommand.PopBackStackTo -> {
                            navController.popBackStack(route = it.to, inclusive = it.inclusive)
                        }

                        ComposeNavigatorCommand.PopBackStack -> {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}