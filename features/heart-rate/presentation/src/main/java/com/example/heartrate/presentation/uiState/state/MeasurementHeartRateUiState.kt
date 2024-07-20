package com.example.heartrate.presentation.uiState.state

import android.graphics.Bitmap
import androidx.camera.core.Camera
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.flow.MutableStateFlow
import org.opencv.core.Scalar

data class MeasurementHeartRateUiState(
    val isPPGTechnologySupported: Boolean = false,
    val heartRateResultValue: Int = 0,
    val measurementRatio: String = "0",
    val measurementState: Boolean = true,
    val measurementTime: Float = 0f,
    val imageResult: Bitmap? = null,
    val peaksCount: Int = 0,
    val meanIntensitiesForPPGRegion: MutableStateFlow<List<Scalar>> = MutableStateFlow(emptyList()),
    val measurementIsFinished: Boolean = false,
    var camera: Camera? = null,
    val processCameraProvider: ListenableFuture<ProcessCameraProvider>? = null,
    val addHeartRateRecordStatus: AddHeartRateRecordStatus = AddHeartRateRecordStatus(),
    val startRunning: Boolean = true,
)

data class AddHeartRateRecordStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val unAuthorized: Boolean = false
)
