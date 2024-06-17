package com.example.heart.prediction.data.source.remote.data.dto.execution


import com.google.gson.annotations.SerializedName

data class HeartPredictionDto(
    @SerializedName("message")
    val message: String?,
    @SerializedName("prediction")
    val prediction: Int?
)