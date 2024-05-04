package com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails


import com.google.gson.annotations.SerializedName

data class BookingDetailData(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("data")
    val `data`: List<OfflineBookingDto>?,
    @SerializedName("last_page")
    val lastPage: Int?
)