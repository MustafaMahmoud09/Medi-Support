package com.example.offline.booking.data.source.remote.data.dto.execution.doctorDetails


import com.example.offline.booking.domain.dto.declarations.doctorDetails.IDate
import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("date")
    override val date: String?,
    @SerializedName("id")
    override val id: Long?
): IDate