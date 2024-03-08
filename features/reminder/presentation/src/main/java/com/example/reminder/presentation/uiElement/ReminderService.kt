package com.example.reminder.presentation.uiElement

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sharedui.R
import com.example.reminder.presentation.uiState.viewModel.ReminderServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReminderService : Service() {

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    @Inject
    lateinit var viewModel: ReminderServiceViewModel

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //show foreground service notification here
        showForegroundSeNotification()


        //for infinite loop
        return super.onStartCommand(intent, flags, startId)

    }//end onStartCommand

    private fun showForegroundSeNotification() {

        //check sdk version greater or equal than 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CoroutineScope(Dispatchers.IO).launch {

                viewModel.state.collectLatest {

                    val notification = notificationBuilder
                        .setContentTitle(it.reminderNameValue)
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
                        .build()


                    startForeground(1, notification)

                }

            }

        }//end if

    }//end showForegroundSeNotification

}//end ReminderService