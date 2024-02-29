package com.example.bloodsugar.presentation.uiState.viewModel

import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class RecordBloodSugarViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(com.example.bloodsugar.presentation.uiState.state.RecordBloodSugarUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //function for update sugar level state
    fun onSugarLevelChanged(newValue: Float) {

        //update sugar level here
        _state.update {
            it.copy(
                sugarLevel = newValue
            )
        }

    }//end onSugarLevelChanged

}//end RecordBloodSugarViewModel