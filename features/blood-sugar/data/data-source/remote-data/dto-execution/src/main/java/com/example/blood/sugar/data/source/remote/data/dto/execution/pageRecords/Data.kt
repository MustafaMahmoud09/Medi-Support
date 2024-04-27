package com.example.blood.sugar.data.source.remote.data.dto.execution.pageRecords


import com.example.blood.sugar.data.source.remote.data.dto.execution.BloodSugarDto
import com.example.blood.sugar.domain.dto.declarations.pageRecords.IData
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("current_page")
    override val currentPage: Int?,
    @SerializedName("last_page")
    override val lastPage: Int?,
    @SerializedName("Records")
    override val records: List<BloodSugarDto?>?
): IData