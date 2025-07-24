package com.kturker.getircasestudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.kturker.navigation.NavigationCommand
import com.kturker.navigation.NavigationManager

@Composable
internal fun NavigationLaunchedEffect(
    navigationManager: NavigationManager,
    navController: NavController
) {

    LaunchedEffect(key1 = Unit) {
        navigationManager.navigationCommandFlow.collect { navigationCommand ->
            with(navigationCommand) {
                when (this) {
                    is NavigationCommand.OpenScreen -> {
                        if (clearBackStack) {
                            navController.popBackStack()
                        }
                        navController.navigate(route = route) {

                            if (addToBackStack.not()) {
                                popUpTo(route = route)
                            }
                        }
                    }

                    is NavigationCommand.PopBackStackTo -> {
                        navController.popBackStack(route = route, inclusive = inclusive)
                    }

                    NavigationCommand.NavigateUp -> {
                        navController.navigateUp()
                    }

                    NavigationCommand.PopBackStack -> {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}