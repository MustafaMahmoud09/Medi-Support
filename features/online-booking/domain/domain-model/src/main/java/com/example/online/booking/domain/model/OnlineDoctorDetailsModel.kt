package com.example.online.booking.domain.model

data class OnlineDoctorDetailsModel(
    val id: Long,
    val image: String,
    val rating: Float,
    val bio: String,
    val clinicLocation: String,
    val name: String,
    val userRating: Float,
    val price: String,
    val specialization: String,
    val phone: String,
    val active: Boolean
)

