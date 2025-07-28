package com.kturker.getircasestudy.progresscentric

import android.app.Notification
import android.app.Notification.ProgressStyle
import android.app.Notification.ProgressStyle.Point
import android.app.Notification.ProgressStyle.Segment
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.IconCompat
import com.kturker.contract.Dispatchers
import com.kturker.core.presentation.progresscentricnotification.ProgressCentricNotificationManager
import com.kturker.core.presentation.progresscentricnotification.ProgressCentricOrderColors
import com.kturker.core.presentation.progresscentricnotification.ProgressCentricOrderState
import com.kturker.language.ML
import com.kturker.language.StringResourceManager
import com.kturker.uikit.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class ProgressCentricNotificationManagerImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    @Dispatchers.Main dispatcher: CoroutineDispatcher
) : CoroutineScope, ProgressCentricNotificationManager {

    override val coroutineContext: CoroutineContext = dispatcher + Job()

    private lateinit var notificationManager: NotificationManager
    private val channelId = "live_updates_16_channel_id"
    private val channelName = "live_updates_16_channel_name"
    private val notificationId = 4321

    private lateinit var colors: ProgressCentricOrderColors
    private lateinit var stringResourceManager: StringResourceManager

    private val startNotificationChannel = Channel<List<Pair<ProgressCentricOrderState, Long>>>()
    override val startNotification: Flow<List<Pair<ProgressCentricOrderState, Long>>>
        get() = startNotificationChannel.receiveAsFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initialize(
        notifManager: NotificationManager,
        colors: ProgressCentricOrderColors,
        stringResourceManager: StringResourceManager
    ) {
        this.colors = colors
        this.stringResourceManager = stringResourceManager

        notificationManager = notifManager
        val channel = NotificationChannel(
            channelId,
            channelName, IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }

    override fun sendNotification() {
        launch {
            startNotificationChannel.send(orderStates)
        }
    }

    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    override fun updateNotificationStatus(orderState: ProgressCentricOrderState) {
        val notificationBuilder = when (orderState) {
            ProgressCentricOrderState.Confirmed -> {
                buildBaseNotification(context)
                    .setContentTitle(stringResourceManager[ML::orderIsPlaced])
                    .setContentText(stringResourceManager[ML::orderIsConfirming])
                    .setStyle(buildBaseProgressStyle())
            }

            ProgressCentricOrderState.Preparing -> {
                buildBaseNotification(context)
                    .setContentTitle(stringResourceManager[ML::orderIsPreparing])
                    .setContentText(stringResourceManager[ML::orderIsPreparingToDelivery])
                    .setStyle(
                        buildBaseProgressStyle().setProgressTrackerIcon(
                            IconCompat.createWithResource(
                                context, R.drawable.order_being_prepared,
                            ).toIcon(context),
                        ).setProgress(24)
                    )
            }

            ProgressCentricOrderState.Enroute -> {
                buildBaseNotification(context)
                    .setContentTitle(stringResourceManager[ML::orderIsOnTheWay])
                    .setContentText(stringResourceManager[ML::orderIsEnroute])
                    .setStyle(
                        buildBaseProgressStyle()
                            .setProgressTrackerIcon(
                                IconCompat.createWithResource(
                                    context, R.drawable.order_arriving,
                                ).toIcon(context),
                            )
                            .setProgress(50),
                    )
            }


            ProgressCentricOrderState.Arriving -> {
                buildBaseNotification(context)
                    .setContentTitle(stringResourceManager[ML::orderIsArriving])
                    .setContentText(stringResourceManager[ML::orderIsEnjoy])
                    .setStyle(
                        buildBaseProgressStyle()
                            .setProgressTrackerIcon(
                                IconCompat.createWithResource(
                                    context, R.drawable.order_is_arrived,
                                ).toIcon(context),
                            )
                            .setProgress(75),
                    )
            }

            ProgressCentricOrderState.Delivered -> {
                buildBaseNotification(context)
                    .setContentTitle(stringResourceManager[ML::orderIsDelivered])
                    .setContentText(stringResourceManager[ML::orderIsThanks])
                    .setStyle(
                        buildBaseProgressStyle()
                            .setProgressTrackerIcon(
                                IconCompat.createWithResource(
                                    context, R.drawable.order_complete,
                                ).toIcon(context),
                            )
                            .setProgress(100),
                    )

            }
        }

        val notification = notificationBuilder.build()
        notificationManager.notify(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildBaseNotification(
        appContext: Context,
    ): Notification.Builder {
        return Notification.Builder(appContext, channelId)
            .setSmallIcon(com.kturker.getircasestudy.R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setColorized(true)
    }

    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    private fun buildBaseProgressStyle(): ProgressStyle {
        val progressStyle = ProgressStyle()
            .setProgressPoints(
                listOf(
                    Point(25).setColor(colors.color25),
                    Point(50).setColor(colors.color50),
                    Point(75).setColor(colors.color75),
                    Point(100).setColor(colors.colo100)
                )
            ).setProgressSegments(
                listOf(
                    Segment(25).setColor(colors.color25),
                    Segment(25).setColor(colors.color25),
                    Segment(25).setColor(colors.color25),
                    Segment(25).setColor(colors.color25)

                )
            )
        return progressStyle
    }

    private val orderStates = listOf(
        ProgressCentricOrderState.Confirmed to 0L,
        ProgressCentricOrderState.Preparing to 2000L,
        ProgressCentricOrderState.Enroute to 2000L,
        ProgressCentricOrderState.Arriving to 2000L,
        ProgressCentricOrderState.Delivered to 2000L
    )
}