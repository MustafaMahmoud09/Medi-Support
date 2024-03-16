package com.example.reminder.presentation.uiState.viewModel

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewModelScope
import com.example.reminder.domain.usecase.interfaces.IGetNearestRemindersUseCase
import com.example.reminder.presentation.R
import com.example.reminder.presentation.uiElement.receivers.CancelReminderNotificationReceiver
import com.example.reminder.presentation.uiElement.receivers.StopReminderNotificationReceiver
import com.example.reminder.presentation.uiState.state.ReminderServiceUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ReminderServiceViewModel @Inject constructor(
    private val getNearestRemindersUseCase: IGetNearestRemindersUseCase,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat,
    private val context: Context
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ReminderServiceUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        onAlarmMediaPlaySoundCreated()
        onTimeNowChanged()
        getNearestActiveReminder()
        onReminderRemainingTimeChanged()
        onReminderNotificationIsVisible()

    }//end init

    //function for create alarm media play object
    private fun onAlarmMediaPlaySoundCreated() {

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

        //create alarm sound here
        val alarmSound = MediaPlayer.create(context, notificationSoundUri)

        //update alarm sound here
        _state.update {
            it.copy(
                alarmSound = alarmSound
            )
        }//end update

    }//end onAlarmMediaPlaySoundCreated

    //function for change time now each one second
    private fun onTimeNowChanged() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //create infinite loop here
            while (true) {

                //change local time state here
                _state.value.timeNowState.update {
                    LocalTime.now()
                }//end update

                if (
                    state.value.currentNearestReminder.differentDays == 0 &&
                    formatLocalTime(
                        localTime = state.value.currentNearestReminder.time,
                        pattern = "hh:mm:ss a"
                    ) == formatLocalTime(
                        localTime = state.value.timeNowState.value,
                        pattern = "hh:mm:ss a"
                    )

                ) {

                    _state.update {
                        it.copy(
                            lastNearestReminder = state.value.currentNearestReminder
                        )
                    }//end update

                    //change reminder notification visibility state here
                    onReminderNotificationVisibilityChanged(
                        state = true
                    )

                }//end if

                //delay one second here
                delay(1000)

            }//end while

        }//end launch

    }//end onLocalTimeChanged


    //function for get nearest reminder active
    private fun getNearestActiveReminder() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //observe time now state
            state.value.timeNowState.collectLatest { timeNow ->

                //observe use case flow here
                getNearestRemindersUseCase(
                    status = true,
                    localTime = timeNow
                ).collectLatest { reminder ->

                    Log.d("HOW", reminder.toString())

                    //check reminders list is not empty
                    if (reminder.isNotEmpty()) {

                        //change reminder values here
                        _state.update {
                            it.copy(
                                currentNearestReminder = reminder[0],
                                reminderNameValue = reminder[0].name,
                                reminderTimeValue = formatLocalTime(
                                    localTime = reminder[0].time,
                                    pattern = "hh:mm:ss a"
                                )
                            )
                        }//end update

                    }//end if

                }//end getNearestRemindersUseCase

            }//end collectLatest

        }//end viewModelScope

    }//end getNearestActiveReminder


    //function for change reminder remaining time value here
    private fun onReminderRemainingTimeChanged() {

        //create coroutine scope here
        viewModelScope.launch {

            //collect time now flow here
            state.value.timeNowState.collectLatest { timeNow ->

                //calculate remaining time here
                var result = state.value.currentNearestReminder.time.minusNanos(
                    timeNow.toNanoOfDay()
                )

                //update remaining time state here
                _state.update {
                    it.copy(
                        reminderRemainingTimeValue = "${state.value.currentNearestReminder.differentDays} ${
                            formatLocalTime(
                                localTime = result,
                                pattern = "HH:mm:ss"
                            )
                        }"
                    )
                }//end update

            }//end collectLatest

        }//end launch

    }//end onReminderRemainingTimeChanged


    //function for change reminder notification visibility state
    private fun onReminderNotificationVisibilityChanged(state: Boolean) {

        //change reminder notification visibility here
        _state.value.reminderNotificationVisible.update {
            state
        }

    }//end onReminderNotificationVisibilityChanged

    //function for notify reminder notification
    @SuppressLint("MissingPermission")
    private fun onReminderNotificationIsVisible() {

        //create coroutine builder here
        viewModelScope.launch {

            //observe reminder notification visible state here
            state.value.reminderNotificationVisible.collectLatest { visibility ->

                //if state is true execute this body
                if (visibility) {

                    playReminderNotificationSound()

                    //change notification state
                    _state.update {
                        it.copy(
                            alarmNotification = createReminderNotification()
                        )
                    }//end update

                    //show notification here
                    notificationManager.notify(
                        100,
                        state.value.alarmNotification!!
                    )

                    //change reminder notification visibility state here
                    onReminderNotificationVisibilityChanged(
                        state = false
                    )

                }//end if

            }//end collectLatest

        }//end launch

    }//end onReminderNotificationIsVisible

    //function for create reminder notification
    private fun createReminderNotification(): Notification {

        //make cancel button action here
        val cancelReminderIntent =
            Intent(context, CancelReminderNotificationReceiver::class.java)
        val snoozeReminderIntentAction =
            PendingIntent.getBroadcast(
                context,
                0,
                cancelReminderIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

        //make stop button action here
        val stopReminderIntent =
            Intent(context, StopReminderNotificationReceiver::class.java)
        val stopReminderIntentAction =
            PendingIntent.getBroadcast(
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
                } ${
                    formatLocalTime(
                        localTime = state.value.lastNearestReminder.time,
                        pattern = "hh:mm:ss a"
                    )
                }"
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
                ).uppercase(), snoozeReminderIntentAction
            )
            .build()

    }//end createNotification

    //function for play alarm notification sound
    private fun playReminderNotificationSound() {

        //stop reminder sound here
        onReminderSoundStopped()

        //start sound here
        state.value.alarmSound?.start()

    }//end onReminderNotificationPlayed


    //function for stop reminder sound if running
    private fun onReminderSoundStopped() {

        //check pre alarm sound is playing
        if (state.value.alarmSound?.isPlaying == true) {

            //stop pre alarm sound here
            state.value.alarmSound?.apply {
                stop()
                release()
            }

            //update media player value here
            _state.update {
                it.copy(
                    alarmSound = null
                )
            }//end update

            //create alarm media play here
            onAlarmMediaPlaySoundCreated()

        }//end if

    }//end onReminderSoundStopped


    //function for cancel reminder notification
    fun onReminderNotificationCanceled() {

        //stop reminder sound here
        onReminderSoundStopped()

        notificationManager.cancel(100)

    }//end onReminderNotificationCanceled


    fun onReminderNotificationStopped() {

        //stop reminder sound here
        onReminderSoundStopped()

    }//end onReminderNotificationStopped

}//end ReminderServiceViewModel