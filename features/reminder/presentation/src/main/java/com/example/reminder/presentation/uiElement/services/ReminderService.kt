package com.example.reminder.presentation.uiElement.services

import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sharedui.R
import com.example.reminder.presentation.uiState.viewModel.reminderService.ReminderServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class ReminderService : Service() {

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    @Inject
    lateinit var viewModel: ReminderServiceViewModel

//    @Inject
//    @Named("foreground_reminder_notification_id")
//    var notificationId: Int? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //show foreground service notification here
        showForegroundSeNotification()

        // If we get killed, after returning from here, restart
        return START_STICKY

    }//end onStartCommand

    private fun showForegroundSeNotification() {

        //create coroutine builder here
        CoroutineScope(Dispatchers.IO).launch {

            //collect view model state here
            viewModel.state.collectLatest {

                //make remaining notification here
                val notification = notificationBuilder
                    .setContentTitle(
                        getString(
                            R.string.next_medication_appointment
                        )
                    )
                    .setContentText(
                        "${
                            getString(
                                R.string.medication_appointment
                            )
                        } : ${it.reminderTimeValue} \n${
                            getString(
                                R.string.the_remaining_time
                            )
                        } : ${it.reminderRemainingTimeValue}"
                    )
                    .setSmallIcon(R.drawable.reminder_icon)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSilent(true)
                    .build()

                    startForeground(1, notification)

            }//end collectLatest

        }//end coroutine scope

    }//end showForegroundSeNotification

}//end ReminderService