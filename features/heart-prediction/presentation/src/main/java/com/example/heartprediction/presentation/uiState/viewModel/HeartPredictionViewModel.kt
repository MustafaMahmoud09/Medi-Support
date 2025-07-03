package com.example.heartprediction.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import com.example.heart.prediction.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.heart.prediction.domain.usecase.declarations.IPredictHeartDiseaseUseCase
import com.example.heartprediction.presentation.uiState.state.HeartPredictionUiState
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
class HeartPredictionViewModel @Inject constructor(
    private val predictHeartDiseaseUseCase: IPredictHeartDiseaseUseCase,
    private val logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(HeartPredictionUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(250)
            _state.update {
                it.copy(
                    startRunning = false
                )
            }
        }//end coroutine builder scope
    }//end init

    //function for change bmi value
    fun onBmiChanged(newValue: String) {

        try {

            if (newValue.isEmpty()) {
                _state.update {
                    it.copy(
                        bmi = ""
                    )
                }//end update
            } else if (newValue.toFloat() in 0f..95f) {

                _state.update {
                    it.copy(
                        bmi = newValue
                    )
                }//end update

            }//end if

        }//end try
        catch (_: Exception) {
        }

    }//end onBmiChanged


    //function for change physical health
    fun onPhysicalHealthChanged(newValue: String) {

        try {

            if (newValue.isEmpty()) {
                _state.update {
                    it.copy(
                        physicalHealth = ""
                    )
                }//end update
            } else if (newValue.toFloat() in 0f..30f) {

                _state.update {
                    it.copy(
                        physicalHealth = newValue
                    )
                }//end update

            }//end if

        }//end try
        catch (_: Exception) {
        }

    }//end onPhysicalHealthChanged


    //function for change mental health
    fun onMentalChanged(newValue: String) {

        try {

            if (newValue.isEmpty()) {
                _state.update {
                    it.copy(
                        mentalHealth = ""
                    )
                }//end update
            } else if (newValue.toFloat() in 0f..30f) {

                _state.update {
                    it.copy(
                        mentalHealth = newValue
                    )
                }//end update

            }//end if

        }//end try
        catch (ex: Exception) {
        }

    }//end onMentalChanged


    //function for change sleep time
    fun onSleepTimeChanged(newValue: String) {

        try {

            if (newValue.isEmpty()) {
                _state.update {
                    it.copy(
                        sleepTime = ""
                    )
                }//end update
            } else if (newValue.toFloat() in 0f..24f) {

                _state.update {
                    it.copy(
                        sleepTime = newValue
                    )
                }//end update

            }//end if

        }//end try
        catch (_: Exception) {
        }

    }//end onSleepTimeChanged


    //function for change sexExpected
    fun onMenuExpectedChanged() {

        _state.update {
            it.copy(
                menuExpanded = !state.value.menuExpanded
            )
        }//end update

    }//end onSexExpectedChanged


    //function for change number of field is focus
    fun onNumberOfFieldFocusChanged(newValue: Int) {

        _state.update {
            it.copy(
                numberOfFieldIsFocus = newValue
            )
        }//end update

    }//end onNumberOfFieldFocusChanged


    //function for change sex selected
    fun onChangeSexSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                sexData = state.value.sexData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeRaceSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                raceData = state.value.raceData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeDiabeticSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                diabeticData = state.value.diabeticData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeAgeCategorySelected(id: Int, name: String) {

        _state.update {
            it.copy(
                ageCategoryData = state.value.ageCategoryData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeGenHealthSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                genHealthData = state.value.genHealthData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeSmokingSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                smokingData = state.value.smokingData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeAlcoholDrinkingSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                alcoholDrinkingData = state.value.alcoholDrinkingData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeStrokeSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                strokeData = state.value.strokeData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeDiffWalkingSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                diffWalkingData = state.value.diffWalkingData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangePhysicalActivitySelected(id: Int, name: String) {

        _state.update {
            it.copy(
                physicalActivityData = state.value.physicalActivityData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeAsthmaSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                asthmaData = state.value.asthmaData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeKidneyDiseaseSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                kidneyDiseaseData = state.value.kidneyDiseaseData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for change sex selected
    fun onChangeSkinCancerSelected(id: Int, name: String) {

        _state.update {
            it.copy(
                skinCancerData = state.value.skinCancerData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected


    //function for make request on use case for heart disease prediction
    fun onHeartDiseasePredicted() {

        viewModelScope.launch(Dispatchers.IO) {

            if (
                state.value.bmi.isNotEmpty() &&
                state.value.mentalHealth.isNotEmpty() &&
                state.value.physicalHealth.isNotEmpty() &&
                state.value.sleepTime.isNotEmpty() &&
                state.value.sexData.id != -1 &&
                state.value.raceData.id != -1 &&
                state.value.diabeticData.id != -1 &&
                state.value.ageCategoryData.id != -1 &&
                state.value.genHealthData.id != -1 &&
                state.value.smokingData.id != -1 &&
                state.value.alcoholDrinkingData.id != -1 &&
                state.value.strokeData.id != -1 &&
                state.value.diffWalkingData.id != -1 &&
                state.value.physicalActivityData.id != -1 &&
                state.value.asthmaData.id != -1 &&
                state.value.kidneyDiseaseData.id != -1 &&
                state.value.skinCancerData.id != -1
            ) {

                try {

                    predictHeartDiseaseUseCase(
                        bmi = state.value.bmi.toFloat(),
                        physicalHealth = state.value.physicalHealth.toFloat(),
                        mentalHealth = state.value.mentalHealth.toFloat(),
                        sleepTime = state.value.sleepTime.toFloat(),
                        ageCategory = state.value.ageCategoryData.id,
                        race = state.value.raceData.id,
                        diabetic = state.value.diabeticData.id,
                        genHealth = state.value.genHealthData.id,
                        sex = state.value.sexData.id,
                        smoking = state.value.smokingData.id,
                        alcoholDrinking = state.value.alcoholDrinkingData.id,
                        stroke = state.value.strokeData.id,
                        diffWalking = state.value.diffWalkingData.id,
                        physicalActivity = state.value.physicalActivityData.id,
                        asthma = state.value.asthmaData.id,
                        kidneyDisease = state.value.kidneyDiseaseData.id,
                        skinCancer = state.value.skinCancerData.id
                    ).collectLatest { status ->

                        when (status) {

                            is Status.Success -> {

                                when (status.toData()?.statusCode) {

                                    200 -> {
                                        _state.update {
                                            it.copy(
                                                predictHeartDiseaseStatus = state.value
                                                    .predictHeartDiseaseStatus.copy(
                                                        success = true,
                                                        loading = false,
                                                    ),
                                                resultClass = status.toData()?.body ?: -1
                                            )
                                        }//end update

                                    }//end success case


                                    401 -> {

                                        logoutFromLocalDatabaseUseCase()
                                        _state.update {
                                            it.copy(
                                                predictHeartDiseaseStatus = state.value
                                                    .predictHeartDiseaseStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        unAuthorized = true
                                                    )
                                            )
                                        }//end update

                                    }//end 401 case

                                    404, 500, 501 -> {

                                        _state.update {
                                            it.copy(
                                                predictHeartDiseaseStatus = state.value
                                                    .predictHeartDiseaseStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.predictHeartDiseaseStatus.serverError
                                                    )
                                            )
                                        }//end update

                                    }//end error server case

                                }//end when

                            }//end success case

                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        predictHeartDiseaseStatus = state.value
                                            .predictHeartDiseaseStatus.copy(
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
                                                predictHeartDiseaseStatus = state.value
                                                    .predictHeartDiseaseStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        internetError = !state.value.predictHeartDiseaseStatus.internetError
                                                    )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                predictHeartDiseaseStatus = state.value
                                                    .predictHeartDiseaseStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.predictHeartDiseaseStatus.serverError
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

                    //failed connected with internet
                    _state.update {
                        it.copy(
                            predictHeartDiseaseStatus = state.value.predictHeartDiseaseStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.predictHeartDiseaseStatus.internetError
                            )
                        )
                    }//end update

                }//end catch

            }//end if
            else {

                //failed connected with internet
                _state.update {
                    it.copy(
                        predictHeartDiseaseStatus = state.value.predictHeartDiseaseStatus.copy(
                            success = false,
                            loading = false,
                            dataNotComplete = !state.value.predictHeartDiseaseStatus.dataNotComplete
                        )
                    )
                }//end update

            }//end else

        }//end coroutine builder scope

    }//end onHeartDiseasePredicted

}//end HeartPredictionViewModel