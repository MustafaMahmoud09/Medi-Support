package com.example.bloodpressure.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blood.pressure.domain.usecase.declarations.IGetLastWeekDiastolicRecordsUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLastWeekSystolicRecordsUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLatestBloodPressureMeasurementUserCase
import com.example.bloodpressure.presentation.uiState.state.StatisticsBloodPressureUiState
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
import com.example.sharedui.uiState.viewModel.BaseViewModel
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.extension.setFieldValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class StatisticsBloodPressureViewModel @Inject constructor(
    private val getLatestBloodPressureMeasurementUserCase: IGetLatestBloodPressureMeasurementUserCase,
    private val getMonthDaysUseCase: IGetMonthDaysUseCase,
    private val getLastWeekSystolicRecordsUseCase: IGetLastWeekSystolicRecordsUseCase,
    private val getLastWeekDiastolicRecordsUseCase: IGetLastWeekDiastolicRecordsUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(StatisticsBloodPressureUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        //get latest blood pressure record
        onGetLatestBloodPressureRecord()

        //get month days
        onGetMonthDays()

        //get Systolic Blood Pressure Result
        onGetSystolicBloodPressureResult()

        //get Diastolic Blood Pressure Result
        onGetDiastolicBloodPressureResult()

    }//end init


    //function for make request for get systolic blood pressure
    private fun onGetSystolicBloodPressureResult() {

        //make coroutine buildr scope here
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //observe get Last Week Systolic flow
                getLastWeekSystolicRecordsUseCase().collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            //get systolic records
                            val systolicRecords = status.toData()?.body

                            Log.d("TAG", systolicRecords.toString())
                            //make default value
                            val measurementDays: LinkedList<String> = LinkedList()
                            val systolicResult: LinkedList<Long> = LinkedList()
                            var maxValue = 0L

                            systolicRecords!!.forEach { model ->
                                //add new day to list contain on measurement days
                                measurementDays.add(model.dayName)
                                //set new entry model represent user measurement
                                systolicResult.add(model.measurementValue)
                                //get max measurement value for complete app logic
                                if (model.measurementValue > maxValue) {
                                    maxValue = model.measurementValue
                                }//end if

                            }//end for

                            //update systolic value
                            _state.update {
                                it.copy(
                                    systolicResult = state.value.systolicResult.copy(
                                        xAxisData = measurementDays,
                                        maxValue = maxValue,
                                        dataResult = setChartEntries(systolicResult),
                                        load = false
                                    )
                                )
                            }//end update

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    systolicResult = state.value.systolicResult.copy(
                                        load = true,
                                    )
                                )
                            }//end update

                        }//end loading case

                        is Status.Error -> {

                            when (status.status) {

                                400 -> {
                                    _state.update {
                                        it.copy(
                                            systolicResult = state.value.systolicResult.copy(
                                                internetError = !state.value.systolicResult.internetError,
                                            )
                                        )
                                    }//end update
                                }//end internet error case

                                500 -> {
                                    _state.update {
                                        it.copy(
                                            systolicResult = state.value.systolicResult.copy(
                                                serverError = !state.value.systolicResult.serverError,
                                            )
                                        )
                                    }//end update
                                }//end server error case

                            }//end when

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: IOException) {

                _state.update {
                    it.copy(
                        systolicResult = state.value.systolicResult.copy(
                            load = false,
                            internetError = !state.value.systolicResult.internetError,
                        )
                    )
                }//end update

            }//end catch

        }//end coroutine builder scope

    }//end onGetSystolicBloodPressureResult


    //function for make request for get diastolic blood pressure
    private fun onGetDiastolicBloodPressureResult() {

        //make coroutine buildr scope here
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //observe get Last Week Systolic flow
                getLastWeekDiastolicRecordsUseCase().collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            //get systolic records
                            val diastolicRecords = status.toData()?.body

                            //make default value
                            val measurementDays = LinkedList<String>()
                            val diastolicResult = LinkedList<Long>()
                            var maxValue = 0L

                            diastolicRecords!!.forEach { model ->
                                //add new day to list contain on measurement days
                                (measurementDays).add(model.dayName)
                                //set new entry model represent user measurement
                                diastolicResult.add(model.measurementValue)
                                //get max measurement value for complete app logic
                                if (model.measurementValue > maxValue) {
                                    maxValue = model.measurementValue
                                }//end if

                            }//end for

                            //update systolic value
                            _state.update {
                                it.copy(
                                    diastolicResult = state.value.diastolicResult.copy(
                                        xAxisData = measurementDays,
                                        maxValue = maxValue,
                                        dataResult = setChartEntries(diastolicResult),
                                        load = false
                                    )
                                )
                            }//end update

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    diastolicResult = state.value.diastolicResult.copy(
                                        load = true,
                                    )
                                )
                            }//end update

                        }//end loading case

                        is Status.Error -> {

                            when (status.status) {

                                400 -> {
                                    _state.update {
                                        it.copy(
                                            diastolicResult = state.value.diastolicResult.copy(
                                                internetError = !state.value.diastolicResult.internetError,
                                            )
                                        )
                                    }//end update
                                }//end internet error case

                                500 -> {
                                    _state.update {
                                        it.copy(
                                            diastolicResult = state.value.diastolicResult.copy(
                                                serverError = !state.value.diastolicResult.serverError,
                                            )
                                        )
                                    }//end update
                                }//end server error case

                            }//end when

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: IOException) {

                _state.update {
                    it.copy(
                        diastolicResult = state.value.diastolicResult.copy(
                            load = false,
                            internetError = !state.value.diastolicResult.internetError,
                        )
                    )
                }//end update

            }//end catch

        }//end coroutine builder scope

    }//end onGetSystolicBloodPressureResult


    //function for get latest blood pressure measurement
    private fun onGetLatestBloodPressureRecord() {

        //create coroutine scope
        viewModelScope.launch(Dispatchers.IO) {

            //make get latest blood pressure use case
            //observe use case flow
            //collect advice model data
            getLatestBloodPressureMeasurementUserCase().collectLatest { bloodPressureRecords ->

                if (bloodPressureRecords.isNotEmpty()) {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceBloodPressureModel = bloodPressureRecords[0]
                        )
                    }//end update

                }//end if
                else {

                    //update advice blood pressure model state
                    _state.update {
                        it.copy(
                            adviceBloodPressureModel = null
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

}//end StatisticsBloodPressureViewModel