package com.example.reminder.presentation.uiState.viewModel

import com.example.reminder.presentation.uiState.state.AddReminderUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddReminderViewModel : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.example.reminder.presentation.uiState.state.AddReminderUiState())

    //for observe by screen
    val state = _state.asStateFlow()

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
    fun onMedicamentNameChanged(newValue: String){

        //change medicament name by new value here
        _state.update {
            it.copy(
                medicamentName = newValue
            )
        }//end update

    }//end onMedicamentNameChanged

}//end AddReminderViewModel