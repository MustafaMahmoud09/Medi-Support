@file:OptIn(ExperimentalGetImage::class)

package com.example.heartrate.presentation.uiState.viewModel.measurement

import android.content.Context
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.Camera
import androidx.camera.core.CameraControl
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.viewModelScope
import com.example.heart.rate.domain.usecase.declarations.IAddNewHeartRateRecordUseCase
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heart.rate.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.heartrate.presentation.uiState.state.MeasurementHeartRateUiState
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IDetectHeartBeatHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IImageProcessingHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IReflectedLightSignalHelper
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
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import org.opencv.core.Scalar
import java.io.IOException
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class MeasurementHeartRateViewModel @Inject constructor(
    private val context: Context,
    private val imageProcessingHelper: IImageProcessingHelper,
    private val reflectedLightSignalHelper: IReflectedLightSignalHelper,
    private val detectHeartBeatHelper: IDetectHeartBeatHelper,
    private val checkPPGTechnologySupportedUseCase: ICheckPPGTechnologySupportedUseCase,
    private val addNewHeartRateRecordUseCase: IAddNewHeartRateRecordUseCase,
    private val logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(MeasurementHeartRateUiState())

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
        onPPGTechnologySupportedDefined()
        onProcessCameraProviderObjectDefined()
        onMeasurementTimeChanged()
        onHeartBeatsCalculated()

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


    //function function for add measurement to user storage
    private fun onHeartRateMeasurementAdded() {

        //create coroutine builder scope here
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make add blood pressure record use case here
                //observe use case flow
                //collect add blood pressure record status
                addNewHeartRateRecordUseCase(
                    rate = state.value.heartRateResultValue
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            when (status.toData()?.statusCode) {

                                200 -> {
                                    _state.update {
                                        it.copy(
                                            addHeartRateRecordStatus = state.value
                                                .addHeartRateRecordStatus.copy(
                                                    success = true,
                                                    loading = false
                                                )
                                        )
                                    }//end update
                                }//end success case

                                404, 500 -> {
                                    _state.update {
                                        it.copy(
                                            addHeartRateRecordStatus = state.value
                                                .addHeartRateRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.addHeartRateRecordStatus.serverError
                                                )
                                        )
                                    }//end update

                                    //restart measurement again
                                    onHeartRateMeasurementRestart()
                                }//end error server case

                                401 -> {

                                    logoutFromLocalDatabaseUseCase()

                                    _state.update {
                                        it.copy(
                                            addHeartRateRecordStatus = state.value
                                                .addHeartRateRecordStatus.copy(
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
                                    addHeartRateRecordStatus = state.value
                                        .addHeartRateRecordStatus.copy(
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
                                            addHeartRateRecordStatus = state.value
                                                .addHeartRateRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.addHeartRateRecordStatus.internetError
                                                )
                                        )
                                    }//end update

                                }//end internet error case

                                500 -> {

                                    _state.update {
                                        it.copy(
                                            addHeartRateRecordStatus = state.value
                                                .addHeartRateRecordStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.addHeartRateRecordStatus.serverError
                                                )
                                        )
                                    }//end update

                                }//end server error case

                            }//end when

                            //restart measurement again
                            onHeartRateMeasurementRestart()

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: IOException) {

                _state.update {
                    it.copy(
                        addHeartRateRecordStatus = state.value
                            .addHeartRateRecordStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.addHeartRateRecordStatus.internetError
                            )
                    )
                }//end update

                //restart measurement again
                onHeartRateMeasurementRestart()
            }//end catch

        }//end coroutine builder scope

    }//end onHeartRateMeasurementAdded


    //function for restart measurement after failed add record
    private fun onHeartRateMeasurementRestart() {

        //for restart measurement tools to default value
        onHeartRateMeasurementStopped()
        _state.update {
            it.copy(
                measurementIsFinished = false
            )
        }//end update

    }//end onHeartRateMeasurementRestart


    //function for change measurement time state
    private fun onMeasurementTimeChanged() {

        //create coroutine scope here
        viewModelScope.launch(Dispatchers.IO) {

//            var checkMeasurementStopped = 0

            while (true) {

                if (!state.value.measurementIsFinished) {

                    state.value.camera?.cameraControl?.enableTorch(true)

                    //delay one second
                    delay(100)

                    //if measurement stopped
                    if (!state.value.measurementState) {

                        //if measurement stopped to one second
                        //destroy heart rate measurement
//                        if (checkMeasurementStopped == 5) {
                        onHeartRateMeasurementStopped()
//                        }//end if

//                        checkMeasurementStopped += 1

                    }//end if

                    //
                    else {

                        //change measurement time here
                        _state.update {
                            it.copy(
                                measurementTime = state.value.measurementTime + 0.1f
                            )
                        }//end update

//                        checkMeasurementStopped = 0

                    }//end else

                    //if heart rate measurement finished
                    if (state.value.measurementTime.toInt() == 15) {

                        //change measurement is finish state
                        _state.update {
                            it.copy(
                                measurementIsFinished = true
                            )
                        }//end update

                        onHeartRateMeasurementAdded()

                        //for close camera after measurement
                        state.value.camera?.cameraControl?.enableTorch(false)

                    }//end if

                }//end if

            }//end while

        }//end launch

    }//end onMeasurementTimeChanged


    //function for stopped measurement
    private fun onHeartRateMeasurementStopped() {

        //destroy mean intensities
        _state.value.meanIntensitiesForPPGRegion.update {
            emptyList()
        }//end update


        //destroy time and ratio and heart rate result here
        _state.update {
            it.copy(
                measurementTime = 0f,
                measurementRatio = "0",
                heartRateResultValue = 0,
                peaksCount = 0
            )
        }

    }//end onHeartRateMeasurementStopped


    //function for analyzed image for calculate reflected light intensity
    fun onImageAnalysed(imageProxy: ImageProxy) {

        viewModelScope.launch {

            OpenCVLoader.initDebug()

            if (!state.value.measurementIsFinished) {

                //convert image proxy to matrix here
                val matrixImage = imageProcessingHelper.imageProxyToMatrix(
                    image = imageProxy
                )

                //detect ppg region here
                val ppgRegionMask = matrixImage.onPPGRegionDetected()

                //if ppg region percentage greater than or equal 98 ,calculate heart rate
                if (reflectedLightSignalHelper.calculateMaskPercentage(ppgRegionMask) >= 98f) {

                    //image after execute filters
                    val imageFilter = matrixImage.onImageProcessingExecuted()

                    //compute mean intensity in ppg regions
                    val meanIntensityInPPGRegion =
                        reflectedLightSignalHelper.computeMeanIntensityInPPGRegions(
                            image = imageFilter,
                            mask = ppgRegionMask
                        )

                    //change mean intensities state here
                    onMeanIntensitiesChanged(
                        newValue = meanIntensityInPPGRegion
                    )

                    delay(100)

                }//end if

                else {
                    //change measurement state here
                    _state.update {
                        it.copy(
                            measurementState = false,
                        )
                    }//end update
                }//end else

            }//end if

            // Close the ImageProxy
            imageProxy.close()

        }//end try

    }//end onImageAnalysed

    //function for execute processing on image
    private fun Mat.onPPGRegionDetected(): Mat {

        //convert image from rgp to hsv
        //for deal with image color by effectively
        var matrix = imageProcessingHelper.convertToHSV(
            inputMat = this
        )

        //detect ppg region in image
        matrix = reflectedLightSignalHelper.detectPPGRegions(
            hsvMat = matrix
        )

        return matrix

    }//end onImageProcessing

    //function for execute image processing on image
    private fun Mat.onImageProcessingExecuted(): Mat {
//
//        //for apply median blur
//        val matrixImage = imageProcessingHelper.applyMedianBlur(
//            inputMat = this
//        )

        //for apply gaussian blur
        return imageProcessingHelper.applyGaussianBlur(
            inputMat = this
        )

    }//end onImageProcessingExecuted


    //function change mean intensities state
    private fun onMeanIntensitiesChanged(newValue: Scalar) {
//
//        Log.d("TAG", newValue.`val`[0].toString())

        //add new mean intensities here
        val meanIntensitiesForPPGRegion =
            LinkedList(state.value.meanIntensitiesForPPGRegion.value)
        meanIntensitiesForPPGRegion.add(newValue)


        //update mean intensities for PPG region
        _state.value.meanIntensitiesForPPGRegion.update {
            meanIntensitiesForPPGRegion
        }

        //update measurement state here
        _state.update {
            it.copy(
                measurementRatio = "%.1f".format((state.value.measurementTime / 15f) * 100),
                measurementState = true
            )
        }//end update

    }//end onMeanIntensitiesChanged

    //function for calculate heart beats
    private fun onHeartBeatsCalculated() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //collect mean intensities state here
            state.value.meanIntensitiesForPPGRegion.collectLatest { intensities ->

                try {

                    //calculate peaks count
                    val peaksCount = detectHeartBeatHelper.calculatePeeks(
                        list = intensities as LinkedList
                    )

                    //change peaks count and heart rate rate value
                    _state.update {
                        it.copy(
                            peaksCount = peaksCount,
                            heartRateResultValue = detectHeartBeatHelper.calculateHeartRate(
                                beats = peaksCount,
                                time = state.value.measurementTime
                            )
                        )
                    }//end update

                }//end try
                catch (ex: Exception) {
                    ex.message?.let { Log.d("TAG", it) }
                }

            }//end collectLatest

        }//end launch

    }//onHeartBeatsCalculated

    //functions for control on camera
    //function for define object from camera
    fun onCameraObjectDefined(camera: Camera): CameraControl {

        //change camera state here
        _state.update {
            it.copy(
                camera = camera
            )
        }//end change

        return state.value.camera!!.cameraControl

    }//end onCameraObjectDefined

    //function for defined object from process camera provider
    private fun onProcessCameraProviderObjectDefined() {

        //check ppg technology supported or no
        if (state.value.isPPGTechnologySupported) {

            //update process camera provider object here
            _state.update {
                it.copy(
                    processCameraProvider = ProcessCameraProvider.getInstance(context)
                )
            }//end update

        }//end if

    }//end onProcessCameraProviderObjectDefined

    //function for close camera
    private fun onCameraClosed() {

        state.value.camera?.cameraControl?.enableTorch(false)
        state.value.processCameraProvider?.get()?.unbindAll()

    }//end onCameraClosed

    override fun onCleared() {
        super.onCleared()
        onCameraClosed()
    }//end onCleared

}//end MeasurementHeartRateViewModel