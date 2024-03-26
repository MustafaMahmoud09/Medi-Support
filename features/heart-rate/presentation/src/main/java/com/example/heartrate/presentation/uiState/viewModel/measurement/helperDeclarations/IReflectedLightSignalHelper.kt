package com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations

import org.opencv.core.Mat

interface IReflectedLightSignalHelper {

    fun convertToHSV(inputMat: Mat): Mat

    fun detectPPGRegions(hsvMat: Mat): Mat

}//end IReflectedLightSignalHelper