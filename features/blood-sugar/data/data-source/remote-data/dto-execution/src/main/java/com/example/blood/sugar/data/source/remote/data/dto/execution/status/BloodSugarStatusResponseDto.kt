package com.example.blood.sugar.data.source.remote.data.dto.execution.status


import com.example.blood.sugar.domain.dto.declarations.status.IBloodSugarStatusResponseDto
import com.google.gson.annotations.SerializedName

data class BloodSugarStatusResponseDto(
    @SerializedName("data")
    override val `data`: List<BloodSugarStatusDto?>?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IBloodSugarStatusResponseDto