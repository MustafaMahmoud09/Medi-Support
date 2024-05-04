package com.example.online.booking.data.source.remote.data.dto.execution.bookingDetails


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val `data`: List<DataX>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)