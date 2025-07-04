package com.example.online.booking.data.source.remote.data.dto.execution.pageOnlineDoctor


import com.example.online.booking.data.source.remote.data.dto.execution.OnlineDoctorDto
import com.example.online.booking.data.source.remote.data.dto.execution.Pagination
import com.example.online.booking.domain.dto.declarations.pageOnlineDoctor.IOnlineDoctorData
import com.google.gson.annotations.SerializedName

data class OnlineDoctorData(
    @SerializedName("data")
    override val `data`: List<OnlineDoctorDto>?,
    @SerializedName("pagination")
    override val pagination: Pagination?
): IOnlineDoctorData