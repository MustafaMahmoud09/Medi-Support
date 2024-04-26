package com.example.blood.pressure.domain.model

data class SimpleBloodPressureModel(
    val id: Long,
    val systolic: Long,
    val diastolic: Long,
    val type: String,
    val createdAt: String,
    val dayName: String
)
