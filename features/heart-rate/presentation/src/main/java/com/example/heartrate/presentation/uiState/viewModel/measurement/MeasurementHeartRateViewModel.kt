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
import com.example.heart.rate.domain.usecase.declarations.ICheckPPGTechnologySupportedUseCase
import com.example.heartrate.presentation.uiState.state.MeasurementHeartRateUiState
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IImageProcessingHelper
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IReflectedLightSignalHelper
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
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


    //function for analyzed image for calculate reflected light intensity
    fun onImageAnalysed(imageProxy: ImageProxy) {

        OpenCVLoader.initDebug()

        val processingImageResult = imageProxy.onImageProcessing()

        Log.d("TAG", processingImageResult.toString())

        val imageResult = matToBitmap(processingImageResult)

        _state.update {
            it.copy(
                imageResult = imageResult
            )
        }

        // Close the ImageProxy
        imageProxy.close()

    }//end onImageAnalysed

    // تحويل مصفوفة الصورة (Mat) إلى Bitmap
    private fun matToBitmap(mat: Mat): Bitmap {
        // إنشاء Bitmap فارغة لتخزين الصورة
        val bitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888)

        // تحويل مصفوفة الصورة (Mat) إلى Bitmap
        Utils.matToBitmap(mat, bitmap)

        return bitmap
    }

    //function for execute processing on image
    private fun ImageProxy.onImageProcessing(): Mat {

        //convert image proxy to matrix here
        var matrix = imageProcessingHelper.imageProxyToMatrix(
            image = this
        )

        //improve image contrast here
        matrix = imageProcessingHelper.applyImproveImageContrast(
            input = matrix
        )

        //filter out impurities from the image here
        matrix = imageProcessingHelper.applyMedianBlur(
            inputMat = matrix
        )

        //uniformity of lighting here
        matrix = imageProcessingHelper.applyHistogramEqualization(
            inputMat = matrix
        )

        //enhance image colors here
        matrix = imageProcessingHelper.enhanceColors(
            inputMat = matrix
        )

        //increase brightness to image here
        matrix = imageProcessingHelper.increaseBrightness(
            inputMat = matrix
        )

        //detect the edge of the image here
        matrix = imageProcessingHelper.applyCannyEdgeDetection(
            inputMat = matrix
        )

        matrix = reflectedLightSignalHelper.convertToHSV(
            inputMat = matrix
        )

        matrix = reflectedLightSignalHelper.detectPPGRegions(
            hsvMat = matrix
        )

        return matrix

    }//end onImageProcessing

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