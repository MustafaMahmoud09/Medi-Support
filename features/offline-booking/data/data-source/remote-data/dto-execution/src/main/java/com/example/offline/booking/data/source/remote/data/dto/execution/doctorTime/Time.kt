package com.example.offline.booking.data.source.remote.data.dto.execution.doctorTime


import com.example.offline.booking.domain.dto.declarations.doctorTime.ITime
import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("time")
    override val time: String?
): ITime