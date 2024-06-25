package com.example.reminder.presentation.uiState.viewModel.reminderService

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.reminder.domain.usecase.interfaces.ICalculateDifferentDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IGetActiveRemindersSizeUseCase
import com.example.reminder.domain.usecase.interfaces.IGetNearestRemindersUseCase
import com.example.reminder.domain.usecase.interfaces.IGetReminderServiceRunningStateUseCase
import com.example.reminder.domain.usecase.interfaces.ISetReminderServiceRunningStateUseCase
import com.example.reminder.presentation.uiElement.services.ReminderService
import com.example.reminder.presentation.uiState.state.ReminderServiceUiState
import com.example.reminder.presentation.uiState.viewModel.reminderService.helperDeclarations.IReminderNotificationHelper
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ReminderServiceViewModel @Inject constructor(
    private val getNearestRemindersUseCase: IGetNearestRemindersUseCase,
    private val reminderNotificationHelper: IReminderNotificationHelper,
    private val calculateDifferentDaysUseCase: ICalculateDifferentDaysUseCase,
    private val getReminderActiveSizeUseCase: IGetActiveRemindersSizeUseCase,
    private val setReminderServiceRunningStateUseCase: ISetReminderServiceRunningStateUseCase,
    private val getReminderServiceRunningStateUseCase: IGetReminderServiceRunningStateUseCase,
    private val context: Context
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ReminderServiceUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        onReminderServiceStateChanged()
        onTimeNowChanged()
        getNearestActiveReminder()
        onReminderRemainingTimeChanged()
        onReminderNotificationNotified()

    }//end init


    //function for run reminder service
    private fun onReminderServiceStateChanged() {

        val serviceIntent = Intent(context, ReminderService::class.java)

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //collect user reminders here
            getReminderActiveSizeUseCase().collectLatest { remindersSize ->

                try {

                    //check service is running
                    if (remindersSize == 0L) {

                        context.stopService(serviceIntent)

                        //change reminder service state here
                        setReminderServiceRunningStateUseCase(
                            status = false
                        )

                    }//end else if
                    else if (remindersSize > 0L) {

                        //start foreground service here
                        context.startForegroundService(
                            serviceIntent
                        )

                        //change reminder service state here
                        setReminderServiceRunningStateUseCase(
                            status = true
                        )

                    }//end else

                } catch (ex: Exception) {

                    ex.message?.let { Log.e("ERROR", it) }

                }//end ex

            }//end collectLatest

        }//end launch

    }//end runReminderService


    //function for change time now each one second
    private fun onTimeNowChanged() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //create infinite loop here
            while (true) {

                //change local time state here
                _state.value.currentTimeState.update {
                    LocalTime.now()
                }//end update

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
            state.value.currentTimeState.collectLatest { currentTime ->

                //observe use case flow here
                getNearestRemindersUseCase(
                    status = true,
                    localTime = currentTime,
                    currentDayNumber = calculateDifferentDaysUseCase.provideCurrentDay()
                ).collectLatest { reminder ->

                    //if reminders list is not empty
                    if (reminder.isNotEmpty()) {

                        //change reminder values here
                        _state.update {
                            it.copy(
                                currentNearestReminder = reminder[0],
                                reminderTimeValue = formatLocalTime(
                                    localTime = reminder[0].time,
                                    pattern = "hh:mm:ss a"
                                ),
                            )
                        }//end update

                    }//end getNearestRemindersUseCase

                }//end collectLatest

            }//end if

        }//end viewModelScope

    }//end getNearestActiveReminder


    //function for change reminder remaining time value here
    private fun onReminderRemainingTimeChanged() {

        //create coroutine scope here
        viewModelScope.launch {

            //collect time now flow here
            state.value.currentTimeState.collectLatest { timeNow ->

                //calculate remaining time here
                val result = state.value.currentNearestReminder.time.minusNanos(
                    timeNow.toNanoOfDay()
                )

                //get different days
                val differentDays = calculateDifferentDaysUseCase(
                    reminderTime = state.value.currentNearestReminder.time,
                    dayNumber = state.value.currentNearestReminder.dayNumber
                )

                //update remaining time state here
                _state.update {
                    it.copy(
                        reminderRemainingTimeValue = "$differentDays ${
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


    //function for notify reminder notification
    private fun onReminderNotificationNotified() {

        //create coroutine builder for convert processor from sync to async and call suspend function
        viewModelScope.launch(Dispatchers.IO) {

            //observe time now state here
            state.value.currentTimeState.collectLatest { currentTime ->

                DayOfWeek.values().forEach { day ->
                    Log.d("TAG", "${day.value} ${day.name}")
                }

                Log.d("TAG_ordinal", state.value.currentNearestReminder.dayNumber.toString())
                Log.d("TAG_current", LocalDate.now().dayOfWeek.value.toString())

                //if current time equal reminder time notify notification
                if (
                    calculateDifferentDaysUseCase(
                        reminderTime = state.value.currentNearestReminder.time,
                        dayNumber = state.value.currentNearestReminder.dayNumber
                    ) == 0 &&
                    formatLocalTime(
                        localTime = state.value.currentNearestReminder.time,
                        pattern = "hh:mm:ss a"
                    ) == formatLocalTime(
                        localTime = currentTime,
                        pattern = "hh:mm:ss a"
                    )
                ) {

                    //get reminder time here
                    val reminderTime = state.value.currentNearestReminder.time

                    //change notification state
                    _state.update {
                        it.copy(
                            alarmNotification = reminderNotificationHelper.createReminderNotification(
                                time = formatLocalTime(
                                    localTime = reminderTime,
                                    pattern = "hh:mm:ss a"
                                )
                            )
                        )
                    }//end update

                    //play reminder notification sound
                    playReminderNotificationSound()

                    //notify reminder notification
                    reminderNotificationHelper.notifyReminderNotification(
                        notification = state.value.alarmNotification!!
                    )

                }//end if

            }//end collectLatest

        }//end coroutine scope

    }//end onReminderNotificationVisibilityChanged


    //function for play alarm notification sound
    private fun playReminderNotificationSound() {

        //stop reminder sound here
        onReminderSoundStopped()

        //start sound here
        state.value.alarmSound?.start()

    }//end onReminderNotificationPlayed


    //function for cancel reminder notification
    fun onReminderNotificationCanceled() {

        //stop reminder sound here
        onReminderSoundStopped()

        //cancel reminder notification here
        reminderNotificationHelper.cancelReminderNotification()

    }//end onReminderNotificationCanceled


    //function for stop reminder notification
    fun onReminderNotificationStopped() {

        //stop reminder sound here
        onReminderSoundStopped()

    }//end onReminderNotificationStopped


    //function for stop reminder sound if running
    private fun onReminderSoundStopped() {

        //if alarm sound is null create sound notification
        if (state.value.alarmSound == null) {

            //create alarm media play here
            onAlarmMediaPlaySoundCreated()

        }//end if

        //check pre alarm sound is playing
        else if (state.value.alarmSound?.isPlaying == true) {

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


    //function for create alarm media play object
    private fun onAlarmMediaPlaySoundCreated() {

        //update alarm sound here
        _state.update {
            it.copy(
                alarmSound = reminderNotificationHelper.createReminderNotificationSound()
            )
        }//end update

    }//end onAlarmMediaPlaySoundCreated

}//end ReminderServiceViewModel