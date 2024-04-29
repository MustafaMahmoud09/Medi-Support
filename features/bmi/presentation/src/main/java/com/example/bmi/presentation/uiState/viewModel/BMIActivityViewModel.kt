package com.example.bmi.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bmi.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import com.example.bmi.domain.usecase.declarations.IGetLastWeekBMIRecordsUseCase
import com.example.bmi.domain.usecase.declarations.IGetLatestBMIMeasurementUseCase
import com.example.bmi.presentation.uiState.state.BMIActivityUiState
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class BMIActivityViewModel @Inject constructor(
    private val getMonthDaysUseCase: IGetMonthDaysUseCase,
    private val getLatestBMIMeasurementUseCase: IGetLatestBMIMeasurementUseCase,
    private val getLastWeekBMIRecordsUseCase: IGetLastWeekBMIRecordsUseCase,
    private val getLastHistoryRecordsUseCase: IGetLastHistoryRecordsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(BMIActivityUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        onGetMonthDays()

        onGetLastWeekBMIRecords()

        onGetLatestBMIRecord()

        onGetLastHistoryBMIRecords()

    }//end init


    private fun onGetLastWeekBMIRecords() {

        //create coroutine builder scope
        viewModelScope.launch(Dispatchers.IO) {

            //make request on use case for get last week measurements
            getLastWeekBMIRecordsUseCase().collectLatest { chartBMIModels ->

                //make default value
                val measurementDays: LinkedList<String> = LinkedList()
                val measurementResult: LinkedList<Long> = LinkedList()
                var maxValue = 0L

                chartBMIModels.forEach { model ->
                    //add new day to list contain on measurement days
                    measurementDays.add(model.dayName)
                    //set new entry model represent user measurement
                    measurementResult.add(model.result.toLong())
                    //get max measurement value for complete app logic
                    if (model.result > maxValue) {
                        maxValue = model.result.toLong()
                    }//end if

                }//end for

                //update blood sugar chart status here
                _state.update {
                    it.copy(
                        getBMIChartStatus = state.value.getBMIChartStatus.copy(
                            load = false,
                            xAxisData = measurementDays,
                            dataResult = setChartEntries(
                                data = measurementResult
                            ),
                            maxValue = maxValue
                        )
                    )
                }//end update

            }//end collectLatest

        }//end coroutine builder scope

    }//end onGetLastWeekBMIRecords


    //function for get latest blood pressure measurement
    private fun onGetLatestBMIRecord() {

        //create coroutine scope
        viewModelScope.launch(Dispatchers.IO) {

            //make get latest blood pressure use case
            //observe use case flow
            //collect advice model data
            getLatestBMIMeasurementUseCase().collectLatest { bloodPressureRecords ->

                if (bloodPressureRecords.isNotEmpty()) {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceBMIModel = bloodPressureRecords[0]
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

    }//end onGetLatestBMIRecord


    //function for get latest history blood sugar measurement
    private fun onGetLastHistoryBMIRecords() {

        //create coroutine scope
        viewModelScope.launch(Dispatchers.IO) {

            //make get latest blood pressure use case
            //observe use case flow
            //collect advice model data
            getLastHistoryRecordsUseCase().collectLatest { bloodPressureRecords ->


                //update advice blood pressure model state
                _state.update {
                    it.copy(
                        lastHistoryBMIRecords = bloodPressureRecords
                    )
                }//end update

            }//end collectLatest

        }//end coroutine scope

    }//end onGetLastHistoryBMIRecords


    //function for get month days
    private fun onGetMonthDays() {

        //update moth days by current month days
        _state.update {
            it.copy(
                monthDays = getMonthDaysUseCase()
            )
        }//end update

    }//end onGetMonthDays

}//end BloodSugarActivityViewModel