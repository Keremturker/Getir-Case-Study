package com.kturker.getircasestudy

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kturker.feature.product.contract.ProductListScreenDestination
import com.kturker.navigation.NavGraphProvider
import com.kturker.uikit.LocalCustomColorsPalette
import com.kturker.uikit.OnDarkCustomColorsPalette
import com.kturker.uikit.OnLightCustomColorsPalette
import com.kturker.uikit.components.scaffold.KtScaffold

@Composable
internal fun AppRouteScreen(
    navController: NavHostController,
    navGraphProviders: Map<String, NavGraphProvider>
) {
    val isSystemDark =
        if (isSystemInDarkTheme()) OnDarkCustomColorsPalette else OnLightCustomColorsPalette

    CompositionLocalProvider(value = LocalCustomColorsPalette provides isSystemDark) {

        KtScaffold(
            containerColor = LocalCustomColorsPalette.current.backgroundColor
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = ProductListScreenDestination
                ) {
                    navGraphProviders.forEach {
                        it.value.registerGraph(navGraphBuilder = this)
                    }
                }
            }
        }
    }
}