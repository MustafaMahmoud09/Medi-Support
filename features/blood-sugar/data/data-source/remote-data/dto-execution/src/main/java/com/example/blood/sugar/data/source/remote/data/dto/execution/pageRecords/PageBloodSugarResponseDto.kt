package com.example.blood.sugar.data.source.remote.data.dto.execution.pageRecords


import com.example.blood.sugar.domain.dto.declarations.pageRecords.IPageBloodSugarResponseDto
import com.google.gson.annotations.SerializedName

data class PageBloodSugarResponseDto(
    @SerializedName("data")
    override val `data`: Data?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IPageBloodSugarResponseDto