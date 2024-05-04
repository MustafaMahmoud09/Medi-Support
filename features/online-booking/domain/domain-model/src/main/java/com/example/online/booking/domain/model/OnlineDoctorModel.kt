package com.example.online.booking.domain.model

data class OnlineDoctorModel(
    val id: Long,
    val image: String,
    val clinicLocation: String,
    val name: String,
    val rate: Float,
    val workingHours: Int,
    val active: Boolean,
)