package com.example.online.booking.data.source.remote.data.dto.execution.bookingDetails

import com.example.online.booking.data.source.remote.data.dto.execution.Pagination
import com.google.gson.annotations.SerializedName

data class BookingDetailData(
    @SerializedName("data")
    val `data`: List<BookingDetailDto>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)