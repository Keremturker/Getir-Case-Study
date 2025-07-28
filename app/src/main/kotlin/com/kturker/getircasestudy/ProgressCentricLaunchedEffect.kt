package com.kturker.getircasestudy

import android.Manifest
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.kturker.core.presentation.progresscentricnotification.ProgressCentricNotificationManager
import com.kturker.core.presentation.progresscentricnotification.ProgressCentricOrderColors
import com.kturker.language.StringResourceManager
import com.kturker.uikit.LocalCustomColorsPalette
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProgressCentricLaunchedEffect(
    progressCentricNotificationManager: ProgressCentricNotificationManager,
    stringResourceManager: StringResourceManager
) {
    val color = LocalCustomColorsPalette.current

    val notificationManager =
        LocalContext.current.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    progressCentricNotificationManager.initialize(
        notifManager = notificationManager,
        colors = ProgressCentricOrderColors(
            color25 = color.primaryColor.toArgb(),
            color50 = color.primaryColor.toArgb(),
            color75 = color.primaryColor.toArgb(),
            colo100 = color.primaryColor.toArgb()
        ),
        stringResourceManager = stringResourceManager
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BAKLAVA) {
        val permissionsState =
            rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

        SideEffect {
            permissionsState.launchPermissionRequest()
        }

        LaunchedEffect(Unit) {
            progressCentricNotificationManager.startNotification.collect { states ->
                states.forEach {
                    delay(it.second)
                    progressCentricNotificationManager.updateNotificationStatus(it.first)
                }
            }
        }
    }
}
