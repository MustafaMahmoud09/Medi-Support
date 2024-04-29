package com.example.bmi.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bmi.domain.usecase.declarations.IAddNewBMIRecordUseCase
import com.example.bmi.presentation.uiState.state.RecordBMIUiState
import com.example.libraries.core.remote.data.response.status.Status
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
class RecordBMIViewModel @Inject constructor(
    private val addNewBMIRecordUseCase: IAddNewBMIRecordUseCase,
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(RecordBMIUiState())

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
    }//end init

    //function for update gender state
    fun onGenderChanged(newValue: Int) {

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


    //function for add blood pressure record
    fun onBMIRecordAdded() {

        //create coroutine builder scope here
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make add blood pressure record use case here
                //observe use case flow
                //collect add blood pressure record status
                addNewBMIRecordUseCase(
                    height = state.value.height,
                    age = state.value.age.toInt(),
                    weight = state.value.weight,
                    gender = state.value.gender
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            when (status.toData()?.statusCode) {

                                200 -> {
                                    _state.update {
                                        it.copy(
                                            addBMIRecordStatus = state.value
                                                .addBMIRecordStatus.copy(
                                                    success = true,
                                                    loading = false
                                                )
                                        )
                                    }//end update
                                }//end success case

                                404, 500 -> {
                                    _state.update {
                                        it.copy(
                                            addBMIRecordStatus = state.value
                                                .addBMIRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.addBMIRecordStatus.serverError
                                                )
                                        )
                                    }//end update
                                }//end error server case

                            }//end when

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    addBMIRecordStatus = state.value
                                        .addBMIRecordStatus.copy(
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
                                            addBMIRecordStatus = state.value
                                                .addBMIRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.addBMIRecordStatus.internetError
                                                )
                                        )
                                    }//end update

                                }//end internet error case

                                500 -> {

                                    _state.update {
                                        it.copy(
                                            addBMIRecordStatus = state.value
                                                .addBMIRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.addBMIRecordStatus.serverError
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
                        addBMIRecordStatus = state.value
                            .addBMIRecordStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.addBMIRecordStatus.internetError
                            )
                    )
                }//end update

            }//end catch

        }//end coroutine builder scope

    }//end onBloodPressureRecordAdded


}//end RecordBMIViewModel