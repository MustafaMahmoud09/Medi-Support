package com.example.reminder.presentation.uiState.viewModel

import android.annotation.SuppressLint
import android.app.Notification
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewModelScope
import com.example.reminder.domain.usecase.interfaces.IGetNearestRemindersUseCase
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

        onTimeNowChanged()
        getNearestActiveReminder()
        onReminderRemainingTimeChanged()
        onReminderNotificationIsVisible()

    }//end init


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
                    formatLocalTime(
                        localTime = state.value.timeNowState.value,
                        pattern = "hh:mm:ss a"
                    ) == formatLocalTime(
                        localTime = state.value.nearestReminder.time,
                        pattern = "hh:mm:ss a"
                    )
                ) {

                    _state.update {
                        it.copy(
                            lastReminder = state.value.nearestReminder
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
                                nearestReminder = reminder[0],
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
                var result = state.value.nearestReminder.time.minusNanos(
                    timeNow.toNanoOfDay()
                )

                val differentDays = state.value.nearestReminder.differentDays

                //if different days greater than 0 increase 24 * different days - 1
                if (differentDays > 0) {

                    //calculate final result here
                    result = result.plusHours((24 * (differentDays - 1)).toLong())

                }//end if

                //update remaining time state here
                _state.update {
                    it.copy(
                        reminderRemainingTimeValue = formatLocalTime(
                            localTime = result,
                            pattern = "HH:mm:ss"
                        )
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

    @SuppressLint("MissingPermission")
    private fun onReminderNotificationIsVisible() {

        //create coroutine builder here
        viewModelScope.launch {

            //observe reminder notification visible state here
            state.value.reminderNotificationVisible.collectLatest { state ->

                //if state is true execute this body
                if (state) {

                    //show notification here
                    notificationManager.notify(
                        100,
                        createReminderNotification()
                    )

                    //delay 60 second here
                    delay(60 * 1000)

                    //cancel notification here
                    notificationManager.cancel(100)

                    //change reminder notification visibility state here
                    onReminderNotificationVisibilityChanged(
                        state = false
                    )

                }//end if

            }//end collectLatest

        }//end launch

    }//end onReminderNotificationIsVisible

    private fun createReminderNotification(): Notification {

        //get notification sound uri here
        val notificationSoundUri =
            Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.packageName}/raw/reminder_notification_sound")

        //create notification here
        val notification = notificationBuilder
            .setContentTitle(
                "${
                    context.getString(
                        com.example.sharedui.R.string.the_time_now_is
                    )
                } ${
                    formatLocalTime(
                        localTime = state.value.lastReminder.time,
                        pattern = "hh:mm:ss a"
                    )
                }"
            )
            .setContentText(
                context.getString(
                    com.example.sharedui.R.string.remember_you_now_have_an_appointment_to_take_your_medication
                )
            )
            .setSmallIcon(
                com.example.sharedui.R.drawable.alarm_notification
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(
                notificationSoundUri,
                Notification.AUDIO_ATTRIBUTES_DEFAULT.contentType
            )
            .build()

        //return notification here
        return notification

    }//end createNotification

}//end ReminderServiceViewModel