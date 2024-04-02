package com.example.reminder.presentation.uiState.viewModel.reminderService.helperExecution

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.reminder.presentation.R
import com.example.reminder.presentation.uiElement.services.CancelReminderNotificationService
import com.example.reminder.presentation.uiElement.services.StopReminderNotificationService
import com.example.reminder.presentation.uiState.viewModel.reminderService.helperDeclarations.IReminderNotificationHelper

class ReminderNotificationHelper(
    private val context: Context,
    private val notificationBuilder: NotificationCompat.Builder,
    override val reminderNotificationId: Int,
    private val notificationManager: NotificationManagerCompat,
) : IReminderNotificationHelper{

    //function for create reminder notification
    override fun createReminderNotification(
        time: String
    ): Notification {

        //make cancel button action here
        val cancelReminderIntent =
            Intent(context, CancelReminderNotificationService::class.java)
        val cancelReminderIntentAction =
            PendingIntent.getService(
                context,
                0,
                cancelReminderIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

        //make stop button action here
        val stopReminderIntent =
            Intent(context, StopReminderNotificationService::class.java)
        val stopReminderIntentAction =
            PendingIntent.getService(
                context,
                0,
                stopReminderIntent,
                PendingIntent.FLAG_IMMUTABLE
            )


        //create notification here
        return notificationBuilder
            .setContentTitle(
                "${
                    context.getString(
                        com.example.sharedui.R.string.the_time_now_is
                    )
                } $time"
            )
            .setContentText(
                context.getString(
                    com.example.sharedui.R.string.remember_you_now_have_an_appointment_to_take_your_medication
                )
            )
            .setOngoing(true)
            .setSmallIcon(com.example.sharedui.R.drawable.reminder_icon_health_care)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(
                1, context.getString(
                    com.example.sharedui.R.string.stop
                ), stopReminderIntentAction
            )
            .addAction(
                1, context.getString(
                    com.example.sharedui.R.string.cancel
                ).uppercase(), cancelReminderIntentAction
            )
            .addAction(
                1, "", null
            )
            .build()

    }//end createReminderNotification


    //create reminder notification sound here
    override fun createReminderNotificationSound(): MediaPlayer {

        //get notification sound uri here
        val notificationSoundUri =
            Uri.parse(
                "${
                    ContentResolver.SCHEME_ANDROID_RESOURCE
                }://${
                    context.packageName
                }/${
                    R.raw.alarm_notification_sound
                }"
            )

        //return alarm sound here
        return MediaPlayer.create(context, notificationSoundUri)

    }//end createReminderNotificationSound


    //function for notify reminder notification
    @SuppressLint("MissingPermission")
    override fun notifyReminderNotification(notification: Notification) {

        //show notification here
        notificationManager.notify(
            reminderNotificationId,
            notification
        )

    }//end notifyReminderNotification


    //function for cancel reminder notification
    override fun cancelReminderNotification() {

        notificationManager.cancel(reminderNotificationId)

    }//end cancelReminderNotification

}//end ReminderNotificationHelper//end NotificationHelper