package com.example.heartrate.presentation.uiState.viewModel.measurement.helperDeclarations

import org.opencv.core.Scalar

interface IDetectHeartBeatHelper {

    fun calculatePeeks(list: List<Scalar>): Int

    fun calculateHeartRate(beats: Int, time: Float): Int


}//end IDetectHeartBeatHelper