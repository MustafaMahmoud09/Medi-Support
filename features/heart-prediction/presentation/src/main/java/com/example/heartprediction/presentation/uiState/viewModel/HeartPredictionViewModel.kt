package com.example.heartprediction.presentation.uiState.viewModel

import com.example.heartprediction.presentation.uiState.state.HeartPredictionUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HeartPredictionViewModel @Inject constructor() : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(HeartPredictionUiState())

    //for observe by screen
    val state = _state.asStateFlow()


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
        catch (_:Exception){}

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
        catch (_: Exception){}

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
        catch (ex: Exception){}

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
        catch (_: Exception){}

    }//end onSleepTimeChanged


    //function for change sexExpected
    fun onMenuExpectedChanged(){

        _state.update {
            it.copy(
                menuExpanded = !state.value.menuExpanded
            )
        }//end update

    }//end onSexExpectedChanged


    //function for change number of field is focus
    fun onNumberOfFieldFocusChanged(newValue: Int){

        _state.update {
            it.copy(
                numberOfFieldIsFocus = newValue
            )
        }//end update

    }//end onNumberOfFieldFocusChanged


    //function for change sex selected
    fun onChangeSexSelected(id: Int, name: String){

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
    fun onChangeRaceSelected(id: Int, name: String){

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
    fun onChangeDiabeticSelected(id: Int, name: String){

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
    fun onChangeAgeCategorySelected(id: Int, name: String){

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
    fun onChangeGenHealthSelected(id: Int, name: String){

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
    fun onChangeSmokingSelected(id: Int, name: String){

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
    fun onChangeAlcoholDrinkingSelected(id: Int, name: String){

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
    fun onChangeStrokeSelected(id: Int, name: String){

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
    fun onChangeDiffWalkingSelected(id: Int, name: String){

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
    fun onChangePhysicalActivitySelected(id: Int, name: String){

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
    fun onChangeAsthmaSelected(id: Int, name: String){

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
    fun onChangeKidneyDiseaseSelected(id: Int, name: String){

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
    fun onChangeSkinCancerSelected(id: Int, name: String){

        _state.update {
            it.copy(
                skinCancerData = state.value.skinCancerData.copy(
                    id = id,
                    name = name
                )
            )
        }//end update

    }//end onChangeSexSelected

}//end HeartPredictionViewModel