@file:OptIn(ExperimentalGetImage::class)

package com.example.heartrate.presentation.uiState.viewModel.measurement

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.Camera
import androidx.camera.core.CameraControl
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.viewModelScope
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heartrate.presentation.uiState.state.MeasurementHeartRateUiState
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IImageProcessingHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IReflectedLightSignalHelper
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
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.Scalar
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class MeasurementHeartRateViewModel @Inject constructor(
    private val context: Context,
    private val imageProcessingHelper: IImageProcessingHelper,
    private val reflectedLightSignalHelper: IReflectedLightSignalHelper,
    private val checkPPGTechnologySupportedUseCase: ICheckPPGTechnologySupportedUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(MeasurementHeartRateUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {

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


    //function for change measurement time state
    private fun onMeasurementTimeChanged() {

        //create coroutine scope here
        viewModelScope.launch(Dispatchers.IO) {

            var checkMeasurementStopped = 0

            while (true) {

                //delay one second
                delay(100)

                //if measurement stopped
                if (!state.value.measurementState) {

                    //if measurement stopped to one second
                    //destroy heart rate measurement
                    if (checkMeasurementStopped == 10) {
                        onHeartRateMeasurementStopped()
                    }//end if

                    checkMeasurementStopped += 1

                }//end if

                //
                else {

                    //change measurement time here
                    _state.update {
                        it.copy(
                            measurementTime = state.value.measurementTime + 0.1f
                        )
                    }//end update

                    checkMeasurementStopped = 0

                }//end else

                //if heart rate measurement finished
                if (state.value.measurementTime.toInt() == 15) {

                    //change measurement is finish state
                    _state.update {
                        it.copy(
                            measurementIsFinished = true
                        )
                    }

                    break
                }//end if

            }//end while

        }//end launch

    }//end onMeasurementTimeChanged

    //function for stopped measurement
    private fun onHeartRateMeasurementStopped() {

        //destroy mean intensities
        _state.value.meanIntensitiesForPPGRegion.update {
            emptyList()
        }

        //destroy time and ratio and heart rate result here
        _state.update {
            it.copy(
                measurementTime = 0f,
                measurementRatio = 0f,
                heartRateResultValue = 0,
                peaksCount = 0
            )
        }

    }//end onHeartRateMeasurementStopped


    //function for analyzed image for calculate reflected light intensity
    fun onImageAnalysed(imageProxy: ImageProxy) {

        OpenCVLoader.initDebug()

        if (!state.value.measurementIsFinished) {

            //convert image proxy to matrix here
            val matrixImage = imageProcessingHelper.imageProxyToMatrix(
                image = imageProxy
            )

            //detect ppg region here
            val ppgRegionMask = matrixImage.onPPGRegionDetected()

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


//            val imageResult = matToBitmap(ppgRegionMask)
//
//
//            _state.update {
//                it.copy(
//                    imageResult = imageResult
//                )
//            }

        }//end if


        // Close the ImageProxy
        imageProxy.close()

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

        var matrixImage = imageProcessingHelper.applyImproveImageContrast(
            input = this
        )

        matrixImage = imageProcessingHelper.applyMedianBlur(
            inputMat = matrixImage
        )

        matrixImage = imageProcessingHelper.applyHistogramEqualization(
            inputMat = matrixImage
        )

        matrixImage = imageProcessingHelper.enhanceColors(
            inputMat = matrixImage
        )


//        matrixImage = imageProcessingHelper.increaseBrightness(
//            inputMat = matrixImage
//        )

        return matrixImage

    }//end onImageProcessingExecuted


    //function change mean intensities state
    private fun onMeanIntensitiesChanged(newValue: Scalar) {

        val meanBlue = newValue.`val`[0]

        Log.d("TAG", newValue.toString())

        if (meanBlue.toInt() >= 100) {

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
                    measurementRatio = "%.1f".format((state.value.measurementTime / 15f) * 100)
                        .toFloat(),
                    measurementState = true
                )
            }//end update

        }//end if

        else {

            //change measurement state here
            _state.update {
                it.copy(
                    measurementState = false,
                )
            }

        }//end else

    }//end onMeanIntensitiesChanged

    //function for calculate heart beats
    private fun onHeartBeatsCalculated() {

        //create coroutine builder here
        viewModelScope.launch(Dispatchers.IO) {

            //collect mean intensities state here
            state.value.meanIntensitiesForPPGRegion.collectLatest { intensities ->

                if (intensities.size > 2) {

                    val threshold = 0.368

                    //get last 3 Intensity
                    val prevIntensity = intensities[intensities.size - 3].`val`[0]
                    val currentIntensity = intensities[intensities.size - 2].`val`[0]
                    val nextIntensity = intensities[intensities.size - 1].`val`[0]

                    //if exist heart beat now or no
                    if (
                        currentIntensity > prevIntensity + threshold &&
                        currentIntensity > nextIntensity + threshold
                    ) {

                        //change peaks count
                        val peaksCount = state.value.peaksCount + 1

                        //change peaks count and heart rate rate value
                        _state.update {
                            it.copy(
                                peaksCount = peaksCount,
                                heartRateResultValue = if (state.value.measurementTime > 0) {
                                    ((peaksCount.toFloat() / state.value.measurementTime) * 60f).toInt()
                                }//end if
                                else {
                                    0
                                }//end else
                            )
                        }//end update

                    }//end if

                }//end if

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


    // تحويل مصفوفة الصورة (Mat) إلى Bitmap
    private fun matToBitmap(mat: Mat): Bitmap {
        // إنشاء Bitmap فارغة لتخزين الصورة
        val bitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888)

        // تحويل مصفوفة الصورة (Mat) إلى Bitmap
        Utils.matToBitmap(mat, bitmap)

        return bitmap
    }

}//end MeasurementHeartRateViewModel