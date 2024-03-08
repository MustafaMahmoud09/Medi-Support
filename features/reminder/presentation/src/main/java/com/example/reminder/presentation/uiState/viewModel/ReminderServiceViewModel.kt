package com.example.reminder.presentation.uiState.viewModel


import androidx.lifecycle.viewModelScope
import com.example.reminder.domain.usecase.interfaces.IGetNearestRemindersUseCase
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderServiceViewModel @Inject constructor(
    private val getNearestRemindersUseCase: IGetNearestRemindersUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow("")

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        getNearestActiveReminder()

    }//end init

    private fun getNearestActiveReminder() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //observe use case flow here
            getNearestRemindersUseCase(
                status = true
            ).collectLatest { reminder ->

                if (reminder.isNotEmpty()) {
                    _state.value = reminder[0].name
                }
            }//end getNearestRemindersUseCase

        }//end viewModelScope

    }//end getNearestActiveReminder

}//end ReminderServiceViewModel