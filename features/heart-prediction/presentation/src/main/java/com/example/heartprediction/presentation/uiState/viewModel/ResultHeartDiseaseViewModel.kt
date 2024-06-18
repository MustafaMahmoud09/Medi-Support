package com.example.heartprediction.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.heartprediction.presentation.uiElement.screens.prediction.PredictionHeartDiseaseArgs
import com.example.heartprediction.presentation.uiState.state.ResultHeartDiseaseUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ResultHeartDiseaseViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): BaseViewModel(){

    //for manage screen state from view model
    private val _state = MutableStateFlow(ResultHeartDiseaseUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking arguments here
    private val arguments: PredictionHeartDiseaseArgs =
        PredictionHeartDiseaseArgs(
            savedStateHandle
        )

    init {

        _state.update {
            it.copy(
                classResult = arguments.resultClass
            )
        }

        Log.d("TAG_CLASS",state.value.classResult.toString())

    }//end init

}//end ResultHeartDiseaseViewModel