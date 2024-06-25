package com.example.heartrate.presentation.uiState.viewModel.measurement.helperExecution

import android.util.Log
import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IDetectHeartBeatHelper
import org.opencv.core.Scalar
import java.util.LinkedList

class DetectHeartBeatHelper : IDetectHeartBeatHelper {

    //function for calculate number of peeks
    override fun calculatePeeks(list: LinkedList<Scalar>): Int {

        if (list.size >= 6) {

            val newList = LinkedList<Scalar>()

            newList.addAll(list)

            for(index in 0..2){
                newList.removeAt(0)
            }//end for

            val signals = newList.map { signal ->
                signal.`val`[0]
            }//end signals

            Log.d("TAG_SIGNALS", signals.toString())

            Log.d("TAG_Red_SCALE",
                newList.map { signal ->
                    signal.`val`[2]
                }.toString()
            )

            Log.d("TAG_Green_SCALE",
                newList.map { signal ->
                    signal.`val`[1]
                }.toString()
            )

            var peeks = 0
            val maxThreshold = 0.3
            val minThreshold = 0.25

            val result = LinkedList<Int>()

            for (count in 1 until signals.size - 1) {

                if (
                    (signals[count] > signals[count - 1] + maxThreshold &&
                            signals[count] > signals[count + 1] + minThreshold) ||
                    (signals[count] > signals[count - 1] + minThreshold &&
                            signals[count] > signals[count + 1] + maxThreshold)
                ) {
                    peeks += 1
                    result.add(count)
                }//end if

            }//end count

            Log.d("TAG_PEEKS", result.toString())

            return peeks

        }//end if

        return 0

    }//end DetectHeartBeatHelper

    //function for scale signals between 0 and 1
    private fun scaleSignals(signals: List<Double>): List<Double> {

        val minValue = signals.minOrNull() ?: return emptyList()

        val maxValue = signals.maxOrNull() ?: return emptyList()

        return signals.map {
            if (maxValue != minValue || it != minValue) {
                (it - minValue) / (maxValue - minValue)
            } else {
                (0).toDouble()
            }
        }//end map

    }//end scaleSignals

    //function for get mean for each 15 signals
    private fun getMeanSignals(
        signals: List<Double>,
        countItem: Int = 15
    ): List<Double> {

        val result = LinkedList<Double>()

        var countSignals = 0
        var resultFor15Signals: Double = (0).toDouble()

        for (count in signals.indices) {

            countSignals += 1
            resultFor15Signals += signals[count]

            if (countSignals == countItem || count == signals.size - 1) {
                result.add(resultFor15Signals / countSignals)
                countSignals = 0
                resultFor15Signals = (0).toDouble()
            }//end if

        }//end for

        return result

    }//end getMeanSignals


    //function for calculate heart rate
    override fun calculateHeartRate(beats: Int, time: Float): Int {

        return if (time > 0f && beats > 0) {
            ((beats.toFloat() / time) * 60f).toInt()
        } else {
            0
        }

    }//end calculateHeartRate


}//end DetectHeartBeatHelper