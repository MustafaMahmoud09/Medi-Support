package com.example.heart.rate.domain.domain.model

data class SimpleHeartRateModel(
    val id: Long,
    val rate: Long,
    val type: String,
    val createdAt: String,
    val dayName: String
)
