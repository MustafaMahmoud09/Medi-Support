package com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails

import com.example.offline.booking.domain.dto.declarations.bookingDetails.IBookingDetailData
import com.google.gson.annotations.SerializedName

data class BookingDetailData(
    @SerializedName("current_page")
    override val currentPage: Int?,
    @SerializedName("data")
    override val `data`: List<OfflineBookingDto>?,
    @SerializedName("last_page")
    override val lastPage: Int?
): IBookingDetailData