package com.example.blood.sugar.data.source.remote.data.dto.execution.pageRecords


import com.example.blood.sugar.data.source.remote.data.dto.execution.BloodSugarDto
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("last_page")
    val lastPage: Int?,
    @SerializedName("Records")
    val records: List<BloodSugarDto?>?
)