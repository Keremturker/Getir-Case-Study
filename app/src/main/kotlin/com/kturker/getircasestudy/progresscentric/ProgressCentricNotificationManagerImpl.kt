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

private const val POINT_24 = 24
private const val POINT_25 = 25
private const val POINT_50 = 50
private const val POINT_75 = 75
private const val POINT_100 = 100
private const val DELAY_2000 = 2000L
private const val DELAY_ZERO = 0L
private const val NOTIFICATION_ID = 4321

@Suppress("UnnecessaryAnnotationUseSiteTarget")
internal class ProgressCentricNotificationManagerImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    @Dispatchers.Main dispatcher: CoroutineDispatcher
) : CoroutineScope, ProgressCentricNotificationManager {

    override val coroutineContext: CoroutineContext = dispatcher + Job()

    private lateinit var notificationManager: NotificationManager
    private val channelId = "live_updates_16_channel_id"
    private val channelName = "live_updates_16_channel_name"

    private lateinit var colors: ProgressCentricOrderColors
    private lateinit var stringResourceManager: StringResourceManager

    private val startNotificationChannel = Channel<List<Pair<ProgressCentricOrderState, Long>>>()
    override val startNotification: Flow<List<Pair<ProgressCentricOrderState, Long>>>
        get() = startNotificationChannel.receiveAsFlow()

    private val orderStates = listOf(
        ProgressCentricOrderState.Confirmed to DELAY_ZERO,
        ProgressCentricOrderState.Preparing to DELAY_2000,
        ProgressCentricOrderState.Enroute to DELAY_2000,
        ProgressCentricOrderState.Arriving to DELAY_2000,
        ProgressCentricOrderState.Delivered to DELAY_2000
    )

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
            channelName,
            IMPORTANCE_DEFAULT
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
                                context,
                                R.drawable.order_being_prepared
                            ).toIcon(context)
                        ).setProgress(POINT_24)
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
                                    context,
                                    R.drawable.order_arriving
                                ).toIcon(context)
                            )
                            .setProgress(POINT_50)
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
                                    context,
                                    R.drawable.order_is_arrived
                                ).toIcon(context)
                            )
                            .setProgress(POINT_75)
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
                                    context,
                                    R.drawable.order_complete
                                ).toIcon(context)
                            )
                            .setProgress(POINT_100)
                    )
            }
        }

        val notification = notificationBuilder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildBaseNotification(appContext: Context): Notification.Builder {
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
                    Point(POINT_25).setColor(colors.color25),
                    Point(POINT_50).setColor(colors.color50),
                    Point(POINT_75).setColor(colors.color75),
                    Point(POINT_100).setColor(colors.colo100)
                )
            )
            .setProgressSegments(
                listOf(
                    Segment(POINT_25).setColor(colors.color25),
                    Segment(POINT_25).setColor(colors.color25),
                    Segment(POINT_25).setColor(colors.color25),
                    Segment(POINT_25).setColor(colors.color25)

                )
            )
        return progressStyle
    }
}
