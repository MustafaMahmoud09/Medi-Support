package com.example.heartrate.presentation.uiState.viewModel.measurement.helperExecution

import com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations.IDetectHeartBeatHelper
import org.opencv.core.Scalar
import java.util.LinkedList

class DetectHeartBeatHelper : IDetectHeartBeatHelper {

    //function for calculate number of peeks
    override fun calculatePeeks(list: List<Scalar>): Int {

        if (list.size >= 3) {

            val signals = list.map { signal ->
                signal.`val`[2]
            }

            val signalsScale = scaleSignals(
                signals = signals
            )

            val meanSignals = getMeanSignals(
                signals = signalsScale
            )

            var peeks = 0
            val thresholdMean = 0.000235
            val thresholdAboutItem = 0.000335

            for (count in 1 until signalsScale.size - 1) {

                if (
                    signalsScale[count] > meanSignals[count / 15] + thresholdMean &&
                    signalsScale[count] > signalsScale[count - 1] + thresholdAboutItem &&
                    signalsScale[count] > signalsScale[count + 1] + thresholdAboutItem
                ) {
                    peeks += 1
                }//end if

            }//end count

            return peeks

        }//end if

        return 0

    }//end DetectHeartBeatHelper

    //function for scale signals between 0 and 1
    private fun scaleSignals(signals: List<Double>): List<Double> {

        val minValue = signals.minOrNull() ?: return emptyList()

        val maxValue = signals.maxOrNull() ?: return emptyList()

        return signals.map {
            if (maxValue != minValue) {
                (it - minValue) / (maxValue - minValue)
            } else {
                (1).toDouble()
            }
        }//end map

    }//end scaleSignals

    //function for get mean for each 15 signals
    private fun getMeanSignals(signals: List<Double>): List<Double> {

        val result = LinkedList<Double>()

        var countSignals = 0
        var resultFor15Signals: Double = (0).toDouble()

        for (count in signals.indices) {

            countSignals += 1
            resultFor15Signals += signals[count]

            if (countSignals == 15 || count == signals.size - 1) {
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