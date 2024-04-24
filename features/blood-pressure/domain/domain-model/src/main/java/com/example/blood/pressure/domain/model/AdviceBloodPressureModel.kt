package com.example.blood.pressure.domain.model

data class AdviceBloodPressureModel(
    val id: Long,
    val systolic: Long,
    val diastolic: Long,
    val advice: String,
    val type: String,
    val createdAt: String
)