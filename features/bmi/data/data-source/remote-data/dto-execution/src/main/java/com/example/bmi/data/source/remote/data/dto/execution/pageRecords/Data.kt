package com.example.bmi.data.source.remote.data.dto.execution.pageRecords


import com.example.bmi.data.source.remote.data.dto.execution.BMIDto
import com.example.bmi.domain.dto.declarations.pageRecords.IData
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    override val `data`: List<BMIDto>?,
    @SerializedName("links")
    override val links: Links?
): IData