package com.example.bloodsugar.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.blood.sugar.domain.usecase.declarations.IGetLastWeekBloodSugarRecordsUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetLatestBloodSugarMeasurementUseCase
import com.example.bloodsugar.presentation.uiState.state.StatisticsBloodSugarUiState
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
class StatisticsBloodSugarViewModel @Inject constructor(
    private val getMonthDaysUseCase: IGetMonthDaysUseCase,
    private val getLatestBloodSugarMeasurementUseCase: IGetLatestBloodSugarMeasurementUseCase,
    private val getLastWeekBloodSugarRecordsUseCase: IGetLastWeekBloodSugarRecordsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(StatisticsBloodSugarUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        onGetMonthDays()

        onGetLastWeekBloodSugarRecords()

        onGetLatestBloodSugarRecord()

    }//end init

    private fun onGetLastWeekBloodSugarRecords() {

        //create coroutine builder scope
        viewModelScope.launch(Dispatchers.IO) {

            //make request on use case for get last week measurements
            getLastWeekBloodSugarRecordsUseCase().collectLatest { chartBloodSugarModels ->

                //make default value
                val measurementDays: LinkedList<String> = LinkedList()
                val measurementResult: LinkedList<Long> = LinkedList()
                var maxValue = 0L

                chartBloodSugarModels.forEach { model ->
                    //add new day to list contain on measurement days
                    measurementDays.add(model.dayName)
                    //set new entry model represent user measurement
                    measurementResult.add(model.level.toLong())
                    //get max measurement value for complete app logic
                    if (model.level > maxValue) {
                        maxValue = model.level.toLong()
                    }//end if

                }//end for

                //update blood sugar chart status here
                _state.update {
                    it.copy(
                        getBloodSugarChartStatus = state.value.getBloodSugarChartStatus.copy(
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
    private fun onGetLatestBloodSugarRecord() {

        //create coroutine scope
        viewModelScope.launch(Dispatchers.IO) {

            //make get latest blood pressure use case
            //observe use case flow
            //collect advice model data
            getLatestBloodSugarMeasurementUseCase().collectLatest { bloodPressureRecords ->

                if (bloodPressureRecords.isNotEmpty()) {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceBloodSugarModel = bloodPressureRecords[0]
                        )
                    }//end update

                }//end if
                else {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceBloodSugarModel = null
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

}//end StatisticsBloodSugarViewModel