package com.example.heartrate.presentation.uiState.viewModel.measurement.helperExecution

import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IDetectHeartBeatHelper
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

class DetectHeartBeatHelper : IDetectHeartBeatHelper {

    //function for calculate number of peeks
    override fun calculatePeeks(list: List<Scalar>): Int {

        var result = 0

        if (list.size > 2) {

            val signals = list.map { it.`val`[2] }.toDoubleArray()

            val mat = Mat(1, signals.size, CvType.CV_64F)

            mat.put(0, 0, *signals)

            val peeks = Mat()

            val kernel = Mat.ones(3, 1, CvType.CV_64F)

            Imgproc.filter2D(mat, peeks, -1, kernel)

            for (count in 1 until peeks.cols() - 1) {

                val currentPPGIntensity = peeks.get(0, count)[0]
                val prevPPGIntensity = peeks.get(0, count - 1)[0]
                val nextPPGIntensity = peeks.get(0, count + 1)[0]

                val minThreshold = 0.009

                val maxThreshold = 0.5

                if (
                    (currentPPGIntensity > prevPPGIntensity + minThreshold &&
                            currentPPGIntensity - prevPPGIntensity < maxThreshold) &&
                    (currentPPGIntensity > nextPPGIntensity + minThreshold &&
                            currentPPGIntensity - nextPPGIntensity < maxThreshold)
                ) {
                    result += 1
                }//end if

            }//end for

        }//end if

        return result

    }//end DetectHeartBeatHelper


    //function for calculate heart rate
    override fun calculateHeartRate(beats: Int, time: Float): Int {

        return if (time > 0f && beats > 0) {
            ((beats.toFloat() / time) * 60f).toInt()
        } else {
            0
        }

    }//end calculateHeartRate

}//end DetectHeartBeatHelper


//    //function for define peeks or beat
//    override fun calculatePeeks(intensities: List<Scalar>): Int {
//
//        var result = 0
//        if (intensities.isNotEmpty()) {
//
//            Log.d(
//                "SUM",
//                (
//                        intensities[intensities.size - 1].`val`[0] +
//                                intensities[intensities.size - 1].`val`[1] +
//                                intensities[intensities.size - 1].`val`[2]
//                        ).toString()
//            )
//        }
//
//        return result
//
//    }//end isPeek


//    override fun calculateHeartRate(intensities: List<Scalar>, time: Float): Int {
//
//        if (intensities.isNotEmpty()) {
//
//            try {
//
//                // تحويل intensities إلى مصفوفة من القيم العددية (مثلاً، قيم الأزرق)
//                val originalSignal = intensities.map { it.`val`[2] }.toDoubleArray()
//
//                Log.d("signal_array", originalSignal.toString())
//                // تحديد معدل العينة
//                val samplingRate = intensities.size.toFloat() / time
//
//                Log.d("sampling_rate", samplingRate.toString())
//
//                // الحصول على الحجم المقرب لقوة رقم 2
//                val newSize = Integer.highestOneBit(originalSignal.size - 1).shl(1)
//
//                Log.d("new_size", newSize.toString())
//                // إنشاء مصفوفة جديدة للإشارة بحجم قوة للرقم 2 وملء القيم الزائدة بالصفر
//                val paddedSignal = DoubleArray(newSize) { if (it < originalSignal.size) originalSignal[it] else 0.0 }
//
//                Log.d("padding_signal",paddedSignal.toString())
//                 // تطبيق FFT على الإشارة بعد إضافة الحشو
//                val transformer = FastFourierTransformer(DftNormalization.STANDARD)
//                Log.d("transformer", transformer.toString())
//                val spectrum = transformer.transform(paddedSignal, TransformType.FORWARD)
//                Log.d("spectrum", spectrum.toString())
//                // الحصول على التردد السائد
//                val maxIndex = paddedSignal.size / 2  // حيث أن الطيف يتكون من نقاط متعاكسة
//                Log.d("max_index", maxIndex.toString())
//                val maxAbsValueIndex = (0 until maxIndex).maxByOrNull { abs(spectrum[it].real) } ?: 0
//                Log.d("maxAbsValueIndex", maxAbsValueIndex.toString())
//                val dominantFrequency = maxAbsValueIndex.toFloat() * samplingRate / paddedSignal.size
//                Log.d("dominantFrequency", dominantFrequency.toString())
//                // حساب معدل نبضات القلب
//                return (dominantFrequency * 60).toInt()
//
//            } catch (ex: Exception) {
//                Log.d("TAG", ex.message.toString())
//                return 0
//            }
//
//        }
//
//        return 0
//
//    }


//        var resultPeeks =
//        }
//
//        if (list.size > 2) {
//
//            for (count in 1..list.size - 2) {
//
//                val currentIntensity = list[count].`val`[2]
//                val prevIntensity = list[count - 1].`val`[2]
//                val nextIntensity = list[count + 1].`val`[2]
//
//                Log.d("TAG", "$prevIntensity - $currentIntensity - $nextIntensity")
//
//
//                val maxThreshold = 0.5
//
//                if (
//                    (currentIntensity > prevIntensity + minThreshold &&
//                            currentIntensity - prevIntensity <= maxThreshold) &&
//                    (currentIntensity > nextIntensity + minThreshold &&
//                            currentIntensity - prevIntensity <= maxThreshold)
//                ) {
//
//                    Log.d("TAG", "IS PEEK")
//                    resultPeeks++
//
//                }//end if
//
//            }//end for
//
//        }//end if
//
//        return resultPeeks


//val sumOfSquaredDifferences = subList.map { (it - mean) * (it - mean) }.sum()
//return sqrt(sumOfSquaredDifferences / values.size)


//// Function to calculate the threshold dynamically based on signal values using standard deviation
//private fun calculateThresholdUsingStdDev(
//    list: List<Double>,
//    multiplier: Double,
//    subListSize: Int,
//    currentItem: Int
//): Double {
//
//    // Calculate the standard deviation of the signal values
//    val stdDev = calculateStandardDeviation(
//        values = list,
//        subListSize = subListSize,
//        currentItem = currentItem
//    )
//
//    // Calculate the threshold using the standard deviation and the multiplier
//    return stdDev * multiplier
//}//end calculateThresholdUsingStdDev
//
//// Function to calculate the standard deviation of a list of values
//private fun calculateStandardDeviation(
//    values: List<Double>,
//    subListSize: Int,
//    currentItem: Int
//): Double {
//
//    //get sub list
//    val subList = values.subList(
//        if (currentItem - (subListSize / 2) >= 0) currentItem - (subListSize / 2) else 0,
//        if (currentItem + (subListSize / 2) < values.size) currentItem + (subListSize / 2) else values.size - 1
//    )
//
//    //standard deviation
//    return subList.average()
//
//}//end calculateStandardDeviation