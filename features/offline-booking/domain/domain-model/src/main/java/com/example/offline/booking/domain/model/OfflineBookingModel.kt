package com.example.offline.booking.domain.model

data class OfflineBookingModel(
    val id: Long,
    val doctorName: String,
    val specialization: String,
    val clinicLocation: String,
    val doctorId: Long,
    val date: String
)
