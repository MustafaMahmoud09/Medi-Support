package com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations

import androidx.camera.core.ImageProxy
import org.opencv.core.Mat

interface IImageProcessingHelper {

    //function for convert image proxy to matrix
    fun imageProxyToMatrix(image: ImageProxy): Mat


    //function for improve image contrast
    fun applyImproveImageContrast(input: Mat): Mat


    //function for apply median blur
    fun applyMedianBlur(inputMat: Mat): Mat


    //function for apply edge detection on picture
    fun applyCannyEdgeDetection(inputMat: Mat): Mat


    //function for apply histogram equalization
    fun applyHistogramEqualization(inputMat: Mat): Mat


    //function for enhance colors
    fun enhanceColors(inputMat: Mat): Mat


    //function for increase brightness to picture
    fun increaseBrightness(inputMat: Mat): Mat

}//end IImageProcessingHelper