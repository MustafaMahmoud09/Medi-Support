package com.example.heartrate.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.heart.rate.domain.usecase.declarations.IGetLastWeekHeartRateRecordsUseCase
import com.example.heart.rate.domain.usecase.declarations.IGetLatestHeartRateMeasurementUseCase
import com.example.heartrate.presentation.uiState.state.StatisticsHeartRateUiState
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
class StatisticsHeartRateViewModel @Inject constructor(
    private val getMonthDaysUseCase: IGetMonthDaysUseCase,
    private val getLatestHeartRateMeasurementUseCase: IGetLatestHeartRateMeasurementUseCase,
    private val getLastWeekHeartRateRecordsUseCase: IGetLastWeekHeartRateRecordsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(StatisticsHeartRateUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        onGetMonthDays()

        onGetLastWeekHeartRateRecords()

        onGetLatestHeartRateRecord()

    }//end init


    private fun onGetLastWeekHeartRateRecords() {

        //create coroutine builder scope
        viewModelScope.launch(Dispatchers.IO) {

            //make request on use case for get last week measurements
            getLastWeekHeartRateRecordsUseCase().collectLatest { chartBloodSugarModels ->

                //make default value
                val measurementDays: LinkedList<String> = LinkedList()
                val measurementResult: LinkedList<Long> = LinkedList()
                var maxValue = 0L

                chartBloodSugarModels.forEach { model ->
                    //add new day to list contain on measurement days
                    measurementDays.add(model.dayName)
                    //set new entry model represent user measurement
                    measurementResult.add(model.rate)
                    //get max measurement value for complete app logic
                    if (model.rate > maxValue) {
                        maxValue = model.rate
                    }//end if

                }//end for

                //update blood sugar chart status here
                _state.update {
                    it.copy(
                        getHeartRateChartStatus = state.value.getHeartRateChartStatus.copy(
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

    }//end onGetLastWeekBloodSugarRecords


    //function for get latest blood pressure measurement
    private fun onGetLatestHeartRateRecord() {

        //create coroutine scope
        viewModelScope.launch(Dispatchers.IO) {

            //make get latest blood pressure use case
            //observe use case flow
            //collect advice model data
            getLatestHeartRateMeasurementUseCase().collectLatest { bloodPressureRecords ->

                if (bloodPressureRecords.isNotEmpty()) {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceHeartRateModel = bloodPressureRecords[0]
                        )
                    }//end update

                }//end if
                else {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceHeartRateModel = null
                        )
                    }//end update

                }//end else

            }//end collectLatest

        }//end coroutine scope

    }//end onGetLatestBloodPressureRecord


    //function for get month days
    private fun onGetMonthDays() {

        //update moth days by current month days
        _state.update {
            it.copy(
                monthDays = getMonthDaysUseCase()
            )
        }//end update

    }//end onGetMonthDays

}//end StatisticsHeartRateViewModel