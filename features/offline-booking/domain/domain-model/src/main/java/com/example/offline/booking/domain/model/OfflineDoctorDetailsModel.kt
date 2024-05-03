package com.example.offline.booking.domain.model

data class OfflineDoctorDetailsModel(
    val id: Long,
    val image: String,
    val rating: Float,
    val bio: String,
    val clinicLocation: String,
    val name: String,
    val userRating: Float,
    val price: String,
    val specialization: String,
    val dates: List<DateModel>
)

