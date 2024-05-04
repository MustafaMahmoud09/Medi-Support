package com.example.online.booking.data.source.remote.data.dto.execution.bookingDetails

import com.example.online.booking.data.source.remote.data.dto.execution.Pagination
import com.example.online.booking.domain.dto.declarations.bookingDetails.IBookingDetailData
import com.google.gson.annotations.SerializedName

data class BookingDetailData(
    @SerializedName("data")
    override val `data`: List<BookingDetailDto>?,
    @SerializedName("pagination")
    override val pagination: Pagination?
): IBookingDetailData