package com.example.bmi.data.source.remote.data.dto.execution.pageRecords


import com.example.bmi.data.source.remote.data.dto.execution.BMIDto
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val `data`: List<BMIDto>?,
    @SerializedName("links")
    val links: Links?
)