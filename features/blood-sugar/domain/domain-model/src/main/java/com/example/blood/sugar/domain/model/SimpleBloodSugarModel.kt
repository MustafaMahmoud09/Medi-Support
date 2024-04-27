package com.example.blood.sugar.domain.model

data class SimpleBloodSugarModel(
    val id: Long,
    val level: Double,
    val type: String,
    val createdAt: String,
    val dayName: String
)
