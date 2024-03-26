package com.example.heartrate.presentation.uiState.viewModel.measurement.helperExecution

import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IReflectedLightSignalHelper
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

class ReflectedLightSignalHelper : IReflectedLightSignalHelper {//end ReflectedLightSignalHelper

    override fun convertToHSV(inputMat: Mat): Mat {
        val hsvMat = Mat()
        Imgproc.cvtColor(inputMat, inputMat, Imgproc.COLOR_GRAY2BGR)
        Imgproc.cvtColor(inputMat, hsvMat, Imgproc.COLOR_BGR2HSV)
        return hsvMat
    }

    override fun detectPPGRegions(hsvMat: Mat): Mat {
        val lowerThreshold = Scalar(90.0, 60.0, 60.0) // قيمة العتبة السفلية لكل قناة لونية
        val upperThreshold = Scalar(140.0, 255.0, 255.0) // قيمة العتبة العلوية لكل قناة لونية
        val maskedImage = Mat()
        Core.inRange(hsvMat, lowerThreshold, upperThreshold, maskedImage)
        return maskedImage
    }


}