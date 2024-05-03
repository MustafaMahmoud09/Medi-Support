package com.example.offline.booking.domain.model

data class OfflineDoctorModel(
    val id: Long,
    val image: String,
    val clinicLocation: String,
    val name: String,
    val rate: Float,
    val workingHours: Int
)