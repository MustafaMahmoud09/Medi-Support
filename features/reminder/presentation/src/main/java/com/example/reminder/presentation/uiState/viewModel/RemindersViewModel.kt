package com.example.reminder.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.reminder.domaim.domain.model.reminder.ReminderUpdateData
import com.example.reminder.domain.usecase.interfaces.IDeleteReminderUseCase
import com.example.reminder.domain.usecase.interfaces.IGetUserRemindersUseCase
import com.example.reminder.domain.usecase.interfaces.IUpdateReminderStatusUseCase
import com.example.reminder.paginations.RemindersDataSource
import com.example.reminder.presentation.uiState.state.RemindersUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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

        //get current page reminders here
        val currentPageReminders = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            RemindersDataSource(
                getUserRemindersUseCase = getUserRemindersUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change reminders state here
        _state.update {
            it.copy(
                reminders = currentPageReminders
            )
        }//end update

    }//end getReminders


    //function for update reminder status
    fun onReminderStatusUpdated(status: Boolean, reminderId: Long) {

        try {

            //create coroutine builder here
            viewModelScope.launch(Dispatchers.IO) {

                //update status here
                updateReminderStatusUseCase(
                    reminder = ReminderUpdateData(
                        reminderId = reminderId,
                        status = status
                    )
                )

                //update reminder state from ui reminders
                val newReminders = state.value.reminders?.map { reminders ->

                    //map reminders here
                    reminders.map { reminder ->

                        if (reminder.id == reminderId) {
                            reminder.copy(
                                status = status
                            )
                        }//end if

                        else {
                            reminder
                        }//end else

                    }//end child map

                }//end parent map

                _state.update {
                    it.copy(
                        reminders = newReminders
                    )
                }//end update

            }//end launch

        }//end try
        catch (ex: Exception) {

            Log.d("ERROR", ex.message.toString())

        }//end catch

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

    fun onReminderBackupCreated() {

        _state.update {
            it.copy(
                remindersBackup = state.value.reminders
            )
        }

    }//end onReminderBackupCreated

}//end ReminderViewModel