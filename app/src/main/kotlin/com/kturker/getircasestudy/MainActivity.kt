package com.kturker.getircasestudy

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.kturker.core.presentation.progresscentricnotification.ProgressCentricNotificationManager
import com.kturker.feature.cart.contract.CartScreenDestination
import com.kturker.language.StringResourceManager
import com.kturker.navigation.NavGraphProvider
import com.kturker.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var navGraphProviders: Map<String, @JvmSuppressWildcards NavGraphProvider>

    @Inject
    lateinit var stringResourceManager: StringResourceManager

    @Inject
    lateinit var progressCentricNotificationManager: ProgressCentricNotificationManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent = intent)

        stringResourceManager.setLanguage(Locale.getDefault().language)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            AppRouteScreen(
                navController = navController,
                navGraphProviders = navGraphProviders,
                stringResourceManager = stringResourceManager
            )

            NavigationLaunchedEffect(
                navigationManager = navigationManager,
                navController = navController
            )

            ProgressCentricLaunchedEffect(
                progressCentricNotificationManager,
                stringResourceManager
            )
        }
    }

    private fun handleIntent(intent: Intent?) {
        intent?.let {
            when (intent.getStringExtra(SHORTCUT_ID)) {
                ShortCutType.GotoCart.type -> {
                    navigationManager.navigate(CartScreenDestination)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }
}
