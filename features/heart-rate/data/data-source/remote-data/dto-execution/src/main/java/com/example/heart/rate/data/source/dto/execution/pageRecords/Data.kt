package com.example.heart.rate.data.source.dto.execution.pageRecords


import com.example.heart.rate.data.source.dto.execution.HeartRateDto
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("current_page")
    override val currentPage: Int?,
    @SerializedName("last_page")
    override val lastPage: Int?,
    @SerializedName("Records")
    override val records: List<HeartRateDto?>?
): IData