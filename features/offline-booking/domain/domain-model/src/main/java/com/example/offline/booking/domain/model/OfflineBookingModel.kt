package com.example.online.booking.domain.model

data class OnlineBookingModel(
    val id: Long,
    val doctorName: String,
    val specialization: String,
    val activeStatus: Boolean,
    val bookingStatus: Int
)
