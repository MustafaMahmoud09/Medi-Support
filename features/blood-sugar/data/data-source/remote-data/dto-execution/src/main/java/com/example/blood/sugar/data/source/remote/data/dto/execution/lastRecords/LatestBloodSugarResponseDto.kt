package com.example.blood.sugar.data.source.remote.data.dto.execution.lastRecords


import com.example.blood.sugar.data.source.remote.data.dto.execution.BloodSugarDto
import com.example.blood.sugar.domain.dto.declarations.latestRecords.ILatestBloodSugarResponseDto
import com.google.gson.annotations.SerializedName

data class LatestBloodSugarResponseDto(
    @SerializedName("data")
    override val `data`: List<BloodSugarDto?>?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): ILatestBloodSugarResponseDto