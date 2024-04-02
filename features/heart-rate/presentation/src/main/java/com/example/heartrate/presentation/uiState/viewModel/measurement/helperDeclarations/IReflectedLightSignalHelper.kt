package com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations

import org.opencv.core.Mat
import org.opencv.core.Scalar

interface IReflectedLightSignalHelper {

    //function for detect ppg signal regions
    fun detectPPGRegions(hsvMat: Mat): Mat

    //function for detect mean intensity signal in ppg regions
    fun computeMeanIntensityInPPGRegions(image: Mat, mask: Mat): Scalar

    fun calculateMaskPercentage(mask: Mat): Double

}//end IReflectedLightSignalHelper