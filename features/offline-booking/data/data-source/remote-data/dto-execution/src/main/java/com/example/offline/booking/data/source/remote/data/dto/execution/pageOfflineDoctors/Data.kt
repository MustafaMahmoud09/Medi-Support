package com.example.offline.booking.data.source.remote.data.dto.execution.allOfflineDoctors


import com.example.offline.booking.data.source.remote.data.dto.execution.OfflineDoctorDto
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("data")
    val `data`: List<OfflineDoctorDto>?,
    @SerializedName("last_page")
    val lastPage: Int?
)