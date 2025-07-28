package com.kturker.core.presentation.progresscentricnotification

import android.app.NotificationManager
import com.kturker.language.StringResourceManager
import kotlinx.coroutines.flow.Flow

interface ProgressCentricNotificationManager {

    val startNotification: Flow<List<Pair<ProgressCentricOrderState, Long>>>

    fun initialize(
        notifManager: NotificationManager,
        colors: ProgressCentricOrderColors,
        stringResourceManager: StringResourceManager
    )

    fun sendNotification()

    fun updateNotificationStatus(orderState: ProgressCentricOrderState)
}
