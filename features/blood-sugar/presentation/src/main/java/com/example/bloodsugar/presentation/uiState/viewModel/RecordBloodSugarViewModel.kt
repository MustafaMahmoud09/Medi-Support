package com.example.bloodsugar.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blood.sugar.domain.usecase.declarations.IAddNewBloodSugarRecordUseCase
import com.example.blood.sugar.domain.usecase.declarations.IGetBloodSugarStatusUseCase
import com.example.blood.sugar.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.bloodsugar.presentation.uiState.state.RecordBloodSugarUiState
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
class RecordBloodSugarViewModel @Inject constructor(
    private val addNewBloodSugarRecordUseCase: IAddNewBloodSugarRecordUseCase,
    private val getBloodSugarStatusUseCase: IGetBloodSugarStatusUseCase,
    private val getMonthDaysUseCase: IGetMonthDaysUseCase,
    private val logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(RecordBloodSugarUiState())

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
        onGetMonthDays()
        onGetBloodSugarStatus()
    }//end init

    //function for get status
    private fun onGetBloodSugarStatus() {

        //create coroutine builder for call suspend functions in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //call get blood sugar status use case
                //collect latest value from use case flow
                //update ui state by success value
                getBloodSugarStatusUseCase().collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            if (status.toData()?.statusCode == 200) {

                                _state.update {
                                    it.copy(
                                        getBloodSugarStatusState = state.value.getBloodSugarStatusState.copy(
                                            loading = false,
                                            status = status.toData()?.body ?: emptyList()
                                        )
                                    )
                                }//end update

                            }//end if

                            if (status.toData()?.statusCode == 401) {

                                _state.update {
                                    it.copy(
                                        getBloodSugarStatusState = state.value.getBloodSugarStatusState.copy(
                                            loading = false,
                                            unAuthorized = true
                                        )
                                    )
                                }//end update

                            }//end if

                        }//end success case

                        is Status.Error -> {
                        }//end error case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    getBloodSugarStatusState = state.value.getBloodSugarStatusState.copy(
                                        loading = true
                                    )
                                )
                            }//end update

                        }//end loading case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: Exception) {
                ex.message?.let { Log.d("ERROR", it) }
            }//end catch

        }//end launch

    }//end onGetBloodSugarStatus

    //function for update sugar level state
    fun onSugarLevelChanged(newValue: Float) {

        //update sugar level here
        _state.update {
            it.copy(
                sugarLevel = newValue
            )
        }//end update

    }//end onSugarLevelChanged

    //function for update status id state
    fun onStatusIdChanged(newValue: Int) {

        //if new status id not equal current status id
        //update status id by new status id
        if (newValue != state.value.statusId) {

            var statusSelectedName = ""
            for (count in 0 until state.value.getBloodSugarStatusState.status.size) {
                if (newValue == state.value.getBloodSugarStatusState.status[count].id) {
                    statusSelectedName = state.value.getBloodSugarStatusState.status[count].status
                    break
                }
            }


            _state.update {
                it.copy(
                    statusId = newValue,
                    statusNameSelected = statusSelectedName
                )
            }//end update

        }//end if
        //if new status equal current status id
        //clear current status id
        else {
            _state.update {
                it.copy(
                    statusId = -1,
                    statusNameSelected = "Default"
                )
            }//end update
        }//end else

    }//end onStatusIdChanged

    //function for reverse status state
    fun onStatusStateReversed() {

        //reverse status state here
        _state.update {
            it.copy(
                statusState = !state.value.statusState
            )
        }//end update

    }//end onStatusStateChanged


    //function for add blood pressure record
    fun onBloodSugarRecordAdded() {

        //create coroutine builder scope here
        viewModelScope.launch(Dispatchers.IO) {

            if (state.value.statusId != -1) {

                try {

                    //make add blood pressure record use case here
                    //observe use case flow
                    //collect add blood pressure record status
                    addNewBloodSugarRecordUseCase(
                        level = state.value.sugarLevel,
                        statusId = state.value.statusId
                    ).collectLatest { status ->

                        when (status) {

                            is Status.Success -> {

                                when (status.toData()?.statusCode) {

                                    200 -> {
                                        _state.update {
                                            it.copy(
                                                addBloodSugarRecordStatus = state.value
                                                    .addBloodSugarRecordStatus.copy(
                                                        success = true,
                                                        loading = false
                                                    )
                                            )
                                        }//end update
                                    }//end success case

                                    404, 500 -> {
                                        _state.update {
                                            it.copy(
                                                addBloodSugarRecordStatus = state.value
                                                    .addBloodSugarRecordStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.addBloodSugarRecordStatus.serverError
                                                    )
                                            )
                                        }//end update
                                    }//end error server case

                                    401 -> {

                                        _state.update {
                                            it.copy(
                                                addBloodSugarRecordStatus = state.value
                                                    .addBloodSugarRecordStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        unAuthorized = true
                                                    )
                                            )
                                        }//end update

                                    }//end 401 case

                                }//end when

                            }//end success case

                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        addBloodSugarRecordStatus = state.value
                                            .addBloodSugarRecordStatus.copy(
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
                                                addBloodSugarRecordStatus = state.value
                                                    .addBloodSugarRecordStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        internetError = !state.value.addBloodSugarRecordStatus.internetError
                                                    )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                addBloodSugarRecordStatus = state.value
                                                    .addBloodSugarRecordStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.addBloodSugarRecordStatus.serverError
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
                            addBloodSugarRecordStatus = state.value
                                .addBloodSugarRecordStatus.copy(
                                    success = false,
                                    loading = false,
                                    internetError = !state.value.addBloodSugarRecordStatus.internetError
                                )
                        )
                    }//end update

                }//end catch

            }//end if
            else {

                _state.update {
                    it.copy(
                        addBloodSugarRecordStatus = state.value
                            .addBloodSugarRecordStatus.copy(
                                success = false,
                                loading = false,
                                statusNotSelected = !state.value.addBloodSugarRecordStatus.statusNotSelected
                            )
                    )
                }//end update

            }//end else

        }//end coroutine builder scope

    }//end onBloodPressureRecordAdded


    //function for get month days
    private fun onGetMonthDays() {

        //update moth days by current month days
        _state.update {
            it.copy(
                monthDays = getMonthDaysUseCase()
            )
        }//end update

    }//end onGetMonthDays

}//end RecordBloodSugarViewModel