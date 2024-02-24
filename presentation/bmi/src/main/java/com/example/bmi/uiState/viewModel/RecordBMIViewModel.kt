package com.example.bmi.uiState.viewModel

import com.example.bmi.uiState.state.RecordBMIUiState
import com.example.sharedui.uiState.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class RecordBMIViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(RecordBMIUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //function for update gender state
    fun onGenderChanged(newValue: Boolean) {

        //check new value is not equal gender update gender state
        if (newValue != _state.value.gender) {

            //update gender state here
            _state.update {
                it.copy(
                    gender = newValue
                )
            }//end update

        }//end if

    }//end onGenderChanged

    //function for update age state
    fun onAgeChanged(newValue: Float) {

        //update age state here
        _state.update {
            it.copy(
                age = newValue
            )
        }//end update

    }//end onAgeChanged

    //function for update height state
    fun onHeightChanged(newValue: Float) {

        //update height state here
        _state.update {
            it.copy(
                height = newValue
            )
        }//end update

    }//end onHeightChanged

    //function for update height state
    fun onWeightChanged(newValue: Float) {

        //update weight state here
        _state.update {
            it.copy(
                weight = newValue
            )
        }//end update

    }//end onWeightChanged

}//end RecordBMIViewModel