package com.kturker.getircasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.kturker.navigation.NavGraphProvider
import com.kturker.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var navGraphProviders: Map<String, @JvmSuppressWildcards NavGraphProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            AppRouteScreen(
                navController = navController,
                navGraphProviders = navGraphProviders
            )

            NavigationLaunchedEffect(
                navigationManager = navigationManager,
                navController = navController
            )
        }
    }
}