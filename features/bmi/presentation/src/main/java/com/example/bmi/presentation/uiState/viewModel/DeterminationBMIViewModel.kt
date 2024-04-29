package com.example.bmi.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bmi.domain.usecase.declarations.IGetLatestBMIMeasurementUseCase
import com.example.bmi.presentation.uiState.state.DeterminationBMIUiState
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
class DeterminationBMIViewModel @Inject constructor(
    private val getLatestBMIMeasurementUseCase: IGetLatestBMIMeasurementUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(DeterminationBMIUiState())

    //for observe by screen
    val state = _state.asStateFlow()


    init {
        onGetLatestBloodSugarRecord()
    }//end init

    //function for get latest blood pressure measurement
    private fun onGetLatestBloodSugarRecord() {

        //create coroutine scope
        viewModelScope.launch(Dispatchers.IO) {

            //make get latest blood pressure use case
            //observe use case flow
            //collect advice model data
            getLatestBMIMeasurementUseCase().collectLatest { bmiRecords ->

                if (bmiRecords.isNotEmpty()) {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceBMIModel = bmiRecords[0]
                        )
                    }//end update

                }//end if
                else {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceBMIModel = null
                        )
                    }//end update

                }//end else

            }//end collectLatest

        }//end coroutine scope

    }//end onGetLatestBloodPressureRecord

}//end DeterminationBMIViewModel