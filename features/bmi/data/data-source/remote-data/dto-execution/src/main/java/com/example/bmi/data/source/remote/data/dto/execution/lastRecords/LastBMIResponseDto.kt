package com.example.bmi.data.source.remote.data.dto.execution.lastRecords


import com.example.bmi.data.source.remote.data.dto.execution.BMIDto
import com.example.bmi.domain.dto.declarations.lastReocrds.ILastBMIResponseDto
import com.google.gson.annotations.SerializedName

data class LastBMIResponseDto(
    @SerializedName("data")
    override val `data`: List<BMIDto?>?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): ILastBMIResponseDto