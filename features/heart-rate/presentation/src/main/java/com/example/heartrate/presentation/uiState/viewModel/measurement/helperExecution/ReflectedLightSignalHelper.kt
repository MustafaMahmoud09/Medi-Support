package com.example.heartrate.presentation.uiState.viewModel.measurement.helperExecution

import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IReflectedLightSignalHelper
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Scalar

class ReflectedLightSignalHelper : IReflectedLightSignalHelper {

    //function for detect ppg signal regions
    override fun detectPPGRegions(hsvMat: Mat): Mat {

        //detect region color attributes
        //ppg region signal have red color
        val firstLowerRed = Scalar(0.0, 50.0, 20.0)
        val firstUpperRed = Scalar(5.0, 255.0, 255.0)

        //
        val secondLowerRed = Scalar(175.0, 50.0, 20.0)
        val secondUpperRed = Scalar(180.0, 255.0, 255.0)

        //detect ppg region in image here
        val firstMask = Mat()
        val secondMask = Mat()
        //
        Core.inRange(hsvMat, firstLowerRed, firstUpperRed, firstMask)
        //
        Core.inRange(hsvMat, secondLowerRed, secondUpperRed, secondMask)

        //detect final ppg region in image
        val finalMask = Mat()
        Core.bitwise_or(firstMask, secondMask, finalMask)

        //return image after detected
        return finalMask

    }//end detectPPGRegions


    //function for calculate ppg region Percentage in image
    override fun calculateMaskPercentage(mask: Mat): Double {

        // حساب عدد البكسلات غير الصفر في الصورة المحددة
        val nonZeroPixels = Core.countNonZero(mask)

        // حساب عدد البكسلات الكلي في الصورة الأصلية
        val totalPixels = mask.rows() * mask.cols()

        // حساب النسبة كنسبة مئوية
        val percentage = (nonZeroPixels.toDouble() / totalPixels.toDouble()) * 100.0

        return percentage
    }//end calculateMaskPercentage


    //function for detect mean intensity signal in ppg regions
    override fun computeMeanIntensityInPPGRegions(image: Mat, mask: Mat): Scalar {

        // Compute mean value of pixels in ppg regions
        val meanScalar = Core.mean(image, mask)

        // Extract mean value for each channel
        val meanB = meanScalar.`val`[0]
        val meanG = meanScalar.`val`[1]
        val meanR = meanScalar.`val`[2]

        // Print mean intensity for debugging or logging
        println("Mean intensity in red regions:")
        println("B: $meanB, G: $meanG, R: $meanR")

        // Return the mean intensity as a Scalar object
        return meanScalar

    }//end computeMeanIntensityInPPGRegions

}//end ReflectedLightSignalHelper