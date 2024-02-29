package com.example.bloodpressure.presentation.uiState.viewModel

import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class RecordBloodPressureViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.example.bloodpressure.presentation.uiState.state.RecordBloodPressureUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //function for change systolic blood pressure
    fun onSystolicBloodPressureChanged(signal: Boolean) {

        //check systolic acceptable or no
        if (
            (state.value.systolicBloodPressure > 0 && !signal) ||
            (state.value.systolicBloodPressure < 1000 && signal)
        ) {

            //change systolic blood pressure here
            _state.update {
                it.copy(
                    systolicBloodPressure =
                    /** if signal equal true increment systolic blood pressure by one else - 1 **/
                    if (signal) {
                        state.value.systolicBloodPressure + 1
                    } else {
                        state.value.systolicBloodPressure - 1
                    }
                )
            }//end update

        }//end if

    }//end onSystolicBloodPressureChanged

    //function for change diastolic blood pressure
    fun onDiastolicBloodPressureChanged(signal: Boolean) {

        //check systolic acceptable or no
        if (
            (state.value.systolicBloodPressure > 0 && !signal) ||
            (state.value.systolicBloodPressure < 1000 && signal)
        ) {

            //change diastolic blood pressure here
            _state.update {
                it.copy(
                    diastolicBloodSugar =
                    /** if signal equal true increment diastolic blood pressure by one else - 1 **/
                    if (signal) {
                        state.value.diastolicBloodSugar + 1
                    } else {
                        state.value.diastolicBloodSugar - 1
                    }
                )
            }//end update

        }//end if

    }//end onDiastolicBloodPressureChanged

}//end RecordBloodPressureViewModel