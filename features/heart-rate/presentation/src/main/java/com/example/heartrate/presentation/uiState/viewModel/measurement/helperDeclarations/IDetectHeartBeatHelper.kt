package com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations

import org.opencv.core.Scalar
import java.util.LinkedList

interface IDetectHeartBeatHelper {

    fun calculatePeeks(list: LinkedList<Scalar>): Int

    fun calculateHeartRate(beats: Int, time: Float): Int


}//end IDetectHeartBeatHelper