package com.example.heartrate.presentation.uiState.viewModel

import android.content.Context
import android.hardware.camera2.CameraManager
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heartrate.presentation.uiState.state.MeasurementHeartRateUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MeasurementHeartRateViewModel @Inject constructor(
    private val context: Context,
    private val checkPPGTechnologySupportedUseCase: ICheckPPGTechnologySupportedUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(MeasurementHeartRateUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

        onPPGTechnologySupportedDefined()

        if (state.value.isPPGTechnologySupported) {

            turnOnFlashCamera()
        }//end if

    }//end init

    //function for define ppg sensor available or no
    private fun onPPGTechnologySupportedDefined() {

        //change is ppg sensor available state here
        _state.update {
            it.copy(
                isPPGTechnologySupported = checkPPGTechnologySupportedUseCase()
            )
        }//end update

    }//end onPPGSensorAvailabilityDefined


    //function for run flash
    private fun turnOnFlashCamera() {

        //get camera manager here
        val cameraManager =
            context.getSystemService(Context.CAMERA_SERVICE) as CameraManager?

        if (cameraManager != null) {

            //get list contain on camera ids
            val cameraIds = cameraManager.cameraIdList

            if (cameraIds.isNotEmpty()) {

                //get camera id here
                val cameraId = cameraIds[0]

                //run flash camera here
                cameraManager.setTorchMode(cameraId, true)

            }//end if

        }//end if

    }//end runFlashCamera


}//end MeasurementHeartRateViewModel