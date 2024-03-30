package com.example.heartrate.presentation.uiState.viewModel.measurement.helperExecution

import androidx.camera.core.ImageProxy
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IImageProcessingHelper
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class ImageProcessingHelper : IImageProcessingHelper {

    //function for convert image proxy to matrix
    override fun imageProxyToMatrix(image: ImageProxy): Mat {

        //convert image proxy to bitmap
        val bitmap = image.toBitmap()

        // Create a new Matrix object
        val mat = Mat()

        // Convert the Bitmap to a Mat object
        Utils.bitmapToMat(bitmap, mat)

        // Return the Mat object
        return mat
    }//end imageProxyToMatrix


    //function for convert image from rgb to hsv system
    //to deal with image color by effectively
    override fun convertToHSV(inputMat: Mat): Mat {

        //create empty matrix for result
        val hsvMat = Mat()
        //convert image from rgb to hsv
        Imgproc.cvtColor(inputMat, hsvMat, Imgproc.COLOR_RGB2HSV)
        //return result matrix
        return hsvMat

    }//end convertToHSV



    //function for improve image contrast
    override fun applyImproveImageContrast(input: Mat): Mat {

        //convert image to gray matrix
        val grayMat = Mat(input.rows(), input.cols(), CvType.CV_8UC1)
        Imgproc.cvtColor(input, grayMat, Imgproc.COLOR_RGB2GRAY)

        //Improve image contrast
        val equalizedMat = Mat(input.rows(), input.cols(), CvType.CV_8UC1)
        Imgproc.equalizeHist(grayMat, equalizedMat)

        //return to image improved
        return equalizedMat

    }//end applyImproveImageContrast

    //function for apply median blur
    override fun applyMedianBlur(inputMat: Mat): Mat {

        //create empty matrix for output
        val outputMat = Mat()

        //apply median blur on image
        Imgproc.medianBlur(inputMat, outputMat, 7)

        //return output image after apply median blur
        return outputMat

    }//end applyMedianBlur
//
//    //function for apply edge detection on picture
//    override fun applyCannyEdgeDetection(inputMat: Mat): Mat {
//
//        // إنشاء مصفوفة لتخزين الصورة المحسنة
//        val edgeMat = Mat()
//
//        // حساب القيم الأقصى والأدنى للبكسلات
//        val minMaxValues = Core.minMaxLoc(inputMat)
//        val maxVal = minMaxValues.maxVal
//        val minVal = minMaxValues.minVal
//
//        // حساب الحدود (thresholds) بناءً على القيم الأقصى والأدنى للبكسلات
//        val threshold1 =
//            minVal + (maxVal - minVal) * 0.1 // قيمة threshold1 تكون 10% من الفارق بين القيمة الأقصى والأدنى
//        val threshold2 =
//            minVal + (maxVal - minVal) * 0.3 // قيمة threshold2 تكون 30% من الفارق بين القيمة الأقصى والأدنى
//
//        // تطبيق تقنية Canny لاكتشاف الحواف
//        Imgproc.Canny(inputMat, edgeMat, threshold1, threshold2)
//
//        return edgeMat
//    }//end applyCannyEdgeDetection
//
    //function for apply histogram equalization
    override fun applyHistogramEqualization(inputMat: Mat): Mat {

        // توحيد الهيستوغرام
        val equalizedMat = Mat()
        Imgproc.equalizeHist(inputMat, equalizedMat)

        return equalizedMat
    }//end applyHistogramEqualization

    //function for enhance colors
    override fun enhanceColors(inputMat: Mat): Mat {
        // تحسين تباين الألوان باستخدام توحيد الهيستوغرام
        val equalizedMat = Mat()

        // تعريف المتغير hsvChannels
        val hsvChannels = mutableListOf<Mat>()

        Core.normalize(inputMat, inputMat, 0.0, 255.0, Core.NORM_MINMAX)
        Core.split(inputMat, hsvChannels)
        Imgproc.equalizeHist(hsvChannels[0], hsvChannels[0])
        Core.merge(hsvChannels, equalizedMat)

        // إرجاع الصورة المحسنة
        return equalizedMat
    }//end enhanceColors

    //function for increase brightness to picture
    override fun increaseBrightness(inputMat: Mat): Mat {
        // حساب متوسط السطوع
        val meanValue = Core.mean(inputMat).`val`[0]

        // حساب قيم alpha و beta بناءً على متوسط السطوع
        val targetMeanValue = 127.0 // قيمة متوسط السطوع المستهدفة
        val alpha = targetMeanValue / meanValue
        val beta = 0.0

        // زيادة السطوع باستخدام تحويل الصورة
        val enhancedMat = Mat()
        inputMat.convertTo(enhancedMat, -1, alpha, beta)

        // إرجاع الصورة المحسنة
        return enhancedMat
    }//end increaseBrightness

}//end ImageProcessingHelper