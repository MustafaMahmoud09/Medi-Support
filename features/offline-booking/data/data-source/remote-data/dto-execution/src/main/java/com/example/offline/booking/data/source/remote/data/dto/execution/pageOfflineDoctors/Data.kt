package com.example.offline.booking.data.source.remote.data.dto.execution.pageOfflineDoctors


import com.example.offline.booking.data.source.remote.data.dto.execution.OfflineDoctorDto
import com.example.offline.booking.domain.dto.declarations.pageOfflineDoctors.IData
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("current_page")
    override val currentPage: Int?,
    @SerializedName("data")
    override val `data`: List<OfflineDoctorDto>?,
    @SerializedName("last_page")
    override val lastPage: Int?
): IData