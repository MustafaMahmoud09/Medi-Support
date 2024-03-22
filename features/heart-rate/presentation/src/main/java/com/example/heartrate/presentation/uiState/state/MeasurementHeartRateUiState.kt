package com.example.heartrate.presentation.uiState.state

import androidx.camera.core.Camera
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.common.util.concurrent.ListenableFuture

data class MeasurementHeartRateUiState(
    val isPPGTechnologySupported: Boolean = false,
    val heartRateResultValue: Int = 0,
    val measurementRatio: Int = 0,
    val measurementState: Boolean? = null,
    var camera: Camera? = null,
    val processCameraProvider: ListenableFuture<ProcessCameraProvider>? = null
)
