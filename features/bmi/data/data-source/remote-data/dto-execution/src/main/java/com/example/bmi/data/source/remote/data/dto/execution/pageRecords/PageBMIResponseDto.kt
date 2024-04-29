package com.example.bmi.data.source.remote.data.dto.execution.pageRecords

import com.example.bmi.domain.dto.declarations.pageRecords.IPageBMIResponseDto
import com.google.gson.annotations.SerializedName

data class PageBMIResponseDto(
    @SerializedName("data")
    override val `data`: Data?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IPageBMIResponseDto