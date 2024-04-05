package com.example.reminder.presentation.uiState.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.reminder.domaim.domain.model.reminder.ReminderStoreData
import com.example.reminder.domain.usecase.interfaces.IAddDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IAddReminderUseCase
import com.example.reminder.domain.usecase.interfaces.IGetActiveRemindersSizeUseCase
import com.example.reminder.domain.usecase.interfaces.IGetDaysUseCase
import com.example.reminder.domain.usecase.interfaces.IGetReminderServiceRunningStateUseCase
import com.example.reminder.domain.usecase.interfaces.ISetReminderServiceRunningStateUseCase
import com.example.reminder.presentation.uiElement.services.ReminderService
import com.example.reminder.presentation.uiState.state.AddReminderErrorUiState
import com.example.reminder.presentation.uiState.state.AddReminderUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class AddReminderViewModel @Inject constructor(
    private val addDaysUseCase: IAddDaysUseCase,
    private val getDaysUseCase: IGetDaysUseCase,
    private val addReminderUseCase: IAddReminderUseCase,
    private val getReminderActiveSizeUseCase: IGetActiveRemindersSizeUseCase,
    private val setReminderServiceRunningStateUseCase: ISetReminderServiceRunningStateUseCase,
    private val getReminderServiceRunningStateUseCase: IGetReminderServiceRunningStateUseCase,
    private val context: Context
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(AddReminderUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        onWeekDaysStored()
        getWeekDays()
        onReminderServiceStateChanged()

    }//end init


    //function for run reminder service
    private fun onReminderServiceStateChanged() {

        val serviceIntent = Intent(context, ReminderService::class.java)

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //collect user reminders here
            getReminderActiveSizeUseCase().collectLatest { remindersSize ->

                try {

                    //check service is not running
                    if (
                        remindersSize == 1L &&
                        !getReminderServiceRunningStateUseCase()
                    ) {

                        //start foreground service here
                        context.startForegroundService(serviceIntent)

                        //change reminder service state here
                        setReminderServiceRunningStateUseCase(
                            status = true
                        )

                    }//end if

                    //check service is running
                    else if (
                        remindersSize == 0L &&
                        getReminderServiceRunningStateUseCase()
                    ) {

                        context.stopService(serviceIntent)

                        //change reminder service state here
                        setReminderServiceRunningStateUseCase(
                            status = false
                        )

                    }//end else if

                } catch (ex: Exception) {

                    ex.message?.let { Log.e("ERROR", it) }

                }//end ex

            }//end collectLatest

        }//end launch

    }//end runReminderService


    //function for store week days
    private fun onWeekDaysStored() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //store days here
            addDaysUseCase()

        }//end launch

    }//end onWeekDaysStored

    //function for get week days
    private fun getWeekDays() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //collect days when arrived from use case here
            getDaysUseCase().collectLatest { days ->

                //update week days state by new days here
                _state.update {
                    it.copy(
                        weekDays = days
                    )
                }//end update

            }//end collectLatest

        }//end launch

    }//end getWeekDays


    //function for manage week days selected
    fun onWeekDaySelected(id: Long) {

        //check if week day selected before
        //remove day from days selected state
        if (id in state.value.daysSelected) {

            //remove day here
            _state.update {
                it.copy(
                    daysSelected = state.value.daysSelected.filter { elementValue ->
                        elementValue != id
                    }//end filter list scope
                )
            }//end update

        }//end if

        //else
        //add day to days selected state
        else {

            //create new list contain on days plus new day
            val newDaysSelectedList = LinkedList(state.value.daysSelected)
            newDaysSelectedList.add(id)

            //update days selected state here
            _state.update {
                it.copy(
                    daysSelected = newDaysSelectedList
                )
            }//end update

        }//end else

    }//end onWeekDaySelected


    //function for cancel new week days and replace that by back up list
    fun onWeekDaysSelectedCanceled() {

        //update days selected state by back up list here
        _state.update {
            it.copy(
                daysSelected = state.value.daysSelectedBackup
            )
        }//end update

    }//end onWeekDaysCanceled

    //function for confirm new week days
    fun onWeekDaysSelectedConfirm() {

        //update days selected back up by new days selected
        _state.update {
            it.copy(
                daysSelectedBackup = state.value.daysSelected
            )
        }

        onDaysValueChanged()

    }//end onWeekDaysConfirm

    //function for change days value
    private fun onDaysValueChanged() {

        //get days selected name
        val days = state.value.weekDays.filter { day ->
            day.id in state.value.daysSelectedBackup
        }

        //create variable for store new days value
        var newDaysValue = ""

        //for loop on days selected
        for (index in days.indices) {

            if (index == days.size - 1) {

                newDaysValue += " ${days[index].name}"
                continue
            }//end if

            if (index == 0) {

                newDaysValue += "${days[index].name},"
                continue
            }//end if

            newDaysValue += " ${days[index].name},"

        }//end for loop

        //update days value state here
        _state.update {
            it.copy(
                daysValue = newDaysValue
            )
        }//end update

    }//end onDaysValueChanged


    //function for manage time selected
    fun onTimeValueChanged(snappedTime: LocalTime) {

        //change time selected by new time here
        _state.update {
            it.copy(
                timeSelected = snappedTime
            )
        }//end update

    }//end onTimeValueChanged

    //function for cancel new week days and replace that by back up list
    fun onTimeSelectedCanceled() {

        //update time selected state by back up here
        _state.update {
            it.copy(
                timeSelected = state.value.timeSelectedBackup
            )
        }//end update

    }//end onWeekDaysCanceled

    //function for confirm new time selected
    fun onTimeSelectedConfirm() {

        //update time selected back up by new time selected
        _state.update {
            it.copy(
                timeSelectedBackup = state.value.timeSelected
            )
        }

        onTimeValueChanged()

    }//end onWeekDaysConfirm

    //function for change time value
    private fun onTimeValueChanged() {

        //update time value here
        _state.update {
            it.copy(
                timeValue = formatLocalTime(
                    localTime = state.value.timeSelected,
                    pattern = "hh:mm a"
                )
            )
        }//end update

    }//end onTimeValueChanged


    //fun for store reminder
    fun onReminderStored(): Boolean {

        var findError = false

        //check user do not select days
        if (state.value.daysValue.trim().isEmpty()) {

            _state.update {
                it.copy(
                    screenError = state.value.screenError.copy(
                        daysError = true
                    )
                )
            }//end update

            findError = true

        }//end if

        //check user do not select time
        if (state.value.timeValue.trim().isEmpty()) {

            _state.update {
                it.copy(
                    screenError = state.value.screenError.copy(
                        timeError = true
                    )
                )
            }//end update

            findError = true

        }//end if

        //check user do not write name
        if (state.value.medicamentName.trim().isEmpty()) {

            _state.update {
                it.copy(
                    screenError = state.value.screenError.copy(
                        medicamentNameError = true
                    )
                )
            }//end update

            findError = true

        }//end if

        if (findError) {

            return false
        }//end if

        return executeStoreReminder()

    }//end onReminderStored

    //function for execute store reminder
    private fun executeStoreReminder(): Boolean {

        //change screen state to loading
        _state.update {
            it.copy(
                isLoading = true,
                screenError = AddReminderErrorUiState(),
            )
        }//end update

        //create coroutine builder here
        val job = viewModelScope.launch(Dispatchers.IO) {

            //store reminder here
            addReminderUseCase(
                reminder = ReminderStoreData(
                    reminderName = state.value.medicamentName.trim(),
                    days = state.value.daysSelectedBackup,
                    time = state.value.timeSelectedBackup
                )
            )

            //change screen state to loading
            _state.update {
                it.copy(
                    isLoading = false,
                    medicamentName = "",
                    daysValue = "",
                    timeValue = "",
                    timeSelected = LocalTime.now(),
                    timeSelectedBackup = LocalTime.now(),
                    daysSelected = emptyList(),
                    daysSelectedBackup = emptyList(),
                )
            }//end update

        }//end launch

        return true
    }//end executeStoreReminder

    //function for change date picker visibility
    fun onDatePickerVisibilityChanged(show: Boolean) {

        //check if new value is not equal current value change value
        if (show != state.value.isDatePickerVisible) {

            //change date picker visibility value here
            _state.update {
                it.copy(
                    isDatePickerVisible = show
                )
            }//end update

        }//end if

    }//en onDatePickerVisibilityChanged


    //function for change medicament name by new value
    fun onMedicamentNameChanged(newValue: String) {

        //change medicament name by new value here
        _state.update {
            it.copy(
                medicamentName = newValue
            )
        }//end update

    }//end onMedicamentNameChanged

}//end AddReminderViewModel