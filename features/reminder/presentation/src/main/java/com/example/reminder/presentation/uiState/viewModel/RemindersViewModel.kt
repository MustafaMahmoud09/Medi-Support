package com.example.reminder.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.reminder.domaim.domain.model.reminder.ReminderUpdateData
import com.example.reminder.domain.usecase.interfaces.IDeleteReminderUseCase
import com.example.reminder.domain.usecase.interfaces.IGetUserRemindersUseCase
import com.example.reminder.domain.usecase.interfaces.IUpdateReminderStatusUseCase
import com.example.reminder.presentation.uiState.state.RemindersUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel @Inject constructor(
    private val getUserRemindersUseCase: IGetUserRemindersUseCase,
    private val updateReminderStatusUseCase: IUpdateReminderStatusUseCase,
    private val deleteReminderUseCase: IDeleteReminderUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(RemindersUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        getReminders()
    }//end init

    //function for get reminder from local database
    private fun getReminders() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //collect reminder from use case here
            getUserRemindersUseCase().collectLatest { reminders ->

                Log.d("TAG", reminders.toString())

                //update reminders state here by new reminders
                _state.update {
                    it.copy(
                        reminders = reminders
                    )
                }

            }//end collectLatest

        }//end launch

    }//end getReminders


    //function for update reminder status
    fun onReminderStatusUpdated(status: Boolean, reminderId: Long) {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //update status here
            updateReminderStatusUseCase(
                reminder = ReminderUpdateData(
                    reminderId = reminderId,
                    status = status
                )
            )

        }//end launch

    }//end onReminderStatusUpdated


    //function for delete reminder
    fun onReminderDeleted(reminderId: Long) {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //delete reminder here
            deleteReminderUseCase(
                reminderId = reminderId
            )

        }//end launch

    }//end onReminderDeleted

}//end ReminderViewModel