package com.example.bloodpressure.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.blood.pressure.domain.usecase.declarations.IAddBloodPressureRecordUseCase
import com.example.blood.pressure.domain.usecase.declarations.IGetLatestBloodPressureMeasurementUserCase
import com.example.bloodpressure.presentation.uiState.state.RecordBloodPressureUiState
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RecordBloodPressureViewModel @Inject constructor(
    private val addBloodPressureRecordUseCase: IAddBloodPressureRecordUseCase,
    private val getLatestBloodPressureMeasurementUserCase: IGetLatestBloodPressureMeasurementUserCase,
    private val getMonthDaysUseCase: IGetMonthDaysUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(RecordBloodPressureUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            delay(250)
            _state.update {
                it.copy(
                    startRunning = false
                )
            }//end update
        }//end coroutine builder scope

        //get latest blood pressure record
        onGetLatestBloodPressureRecord()

        //get month days
        onGetMonthDays()

    }//end init

    //function for get latest blood pressure measurement
    private fun onGetLatestBloodPressureRecord() {

        //create coroutine scope
        viewModelScope.launch(Dispatchers.IO){

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


    //function for change systolic blood pressure
    fun onSystolicBloodPressureChanged(signal: Boolean) {

        //check systolic acceptable or no
        if (
            (state.value.systolicBloodPressure > 0 && !signal) ||
            (state.value.systolicBloodPressure < 100000 && signal)
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
            (state.value.systolicBloodPressure < 100000 && signal)
        ) {

            //change diastolic blood pressure here
            _state.update {
                it.copy(
                    diastolicBloodPressure =
                    /** if signal equal true increment diastolic blood pressure by one else - 1 **/
                    if (signal) {
                        state.value.diastolicBloodPressure + 1
                    } else {
                        state.value.diastolicBloodPressure - 1
                    }
                )
            }//end update

        }//end if

    }//end onDiastolicBloodPressureChanged


    //function for add blood pressure record
    fun onBloodPressureRecordAdded() {

        //create coroutine builder scope here
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make add blood pressure record use case here
                //observe use case flow
                //collect add blood pressure record status
                addBloodPressureRecordUseCase(
                    systolic = state.value.systolicBloodPressure,
                    diastolic = state.value.diastolicBloodPressure
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            when (status.toData()?.statusCode) {

                                201 -> {
                                    _state.update {
                                        it.copy(
                                            addBloodPressureRecordStatus = state.value
                                                .addBloodPressureRecordStatus.copy(
                                                    success = true,
                                                    loading = false
                                                )
                                        )
                                    }//end update
                                }//end success case

                                404,500 -> {
                                    _state.update {
                                        it.copy(
                                            addBloodPressureRecordStatus = state.value
                                                .addBloodPressureRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.addBloodPressureRecordStatus.serverError
                                                )
                                        )
                                    }//end update
                                }//end error server case

                            }//end when

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    addBloodPressureRecordStatus = state.value
                                        .addBloodPressureRecordStatus.copy(
                                            success = false,
                                            loading = true
                                        )
                                )
                            }//end update

                        }//end loading case

                        is Status.Error -> {

                            when (status.status) {

                                400 -> {

                                    _state.update {
                                        it.copy(
                                            addBloodPressureRecordStatus = state.value
                                                .addBloodPressureRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.addBloodPressureRecordStatus.internetError
                                                )
                                        )
                                    }//end update

                                }//end internet error case

                                500 -> {

                                    _state.update {
                                        it.copy(
                                            addBloodPressureRecordStatus = state.value
                                                .addBloodPressureRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.addBloodPressureRecordStatus.serverError
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
                        addBloodPressureRecordStatus = state.value
                            .addBloodPressureRecordStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.addBloodPressureRecordStatus.internetError
                            )
                    )
                }//end update

            }//end catch

        }//end coroutine builder scope

    }//end onBloodPressureRecordAdded

}//end RecordBloodPressureViewModel