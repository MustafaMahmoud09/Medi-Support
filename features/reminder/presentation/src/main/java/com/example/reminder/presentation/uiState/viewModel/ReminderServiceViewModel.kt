package com.example.reminder.presentation.uiState.viewModel

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
    private val getNearestRemindersUseCase: IGetNearestRemindersUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ReminderServiceUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        onTimeNowChanged()
        getNearestActiveReminder()
        onReminderRemainingTimeChanged()

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
            state.value.timeNowState.collectLatest {

                //observe use case flow here
                getNearestRemindersUseCase(
                    status = true,
                    localTime = it
                ).collectLatest { reminder ->

                    //check reminders list is not empty
                    if (reminder.isNotEmpty()) {

                        //change reminder values here
                        _state.update {
                            it.copy(
                                reminderNameValue = reminder[0].name,
                                reminderTimeValue = formatLocalTime(
                                    localTime = reminder[0].time,
                                    "hh:mm:ss a"
                                ),
                                reminderTime = reminder[0].time
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

            //create infinite loop here
            while (true) {

                //calculate remaining time here
                val result = state.value.reminderTime.minusNanos(
                    state
                        .value
                        .timeNowState
                        .value
                        .toNanoOfDay()

                )

                //update remaining time state here
                _state.update {
                    it.copy(
                        reminderRemainingTimeValue = formatLocalTime(
                            localTime = result,
                            pattern = "HH:mm:ss"
                        )
                    )
                }//end update

                //delay one second here
                delay(1000)

            }//end while

        }//end launch

    }//end onReminderRemainingTimeChanged


}//end ReminderServiceViewModel