package com.example.online.booking.data.source.remote.data.dto.execution.pageOnlineDoctor


import com.example.online.booking.data.source.remote.data.dto.execution.OnlineDoctorDto
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val `data`: List<OnlineDoctorDto>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)