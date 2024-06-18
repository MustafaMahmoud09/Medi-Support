package com.example.heart.prediction.data.source.remote.data.dto.execution


import com.example.heart.prediction.domain.dto.declarations.IHeartPredictionDto
import com.google.gson.annotations.SerializedName

data class HeartPredictionDto(
    @SerializedName("message")
    override val message: String?,
    @SerializedName("prediction")
    override val prediction: Int?
): IHeartPredictionDto