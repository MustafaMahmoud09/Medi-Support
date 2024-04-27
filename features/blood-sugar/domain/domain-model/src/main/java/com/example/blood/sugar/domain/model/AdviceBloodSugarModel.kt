package com.example.blood.sugar.domain.model

data class AdviceBloodSugarModel(
    val id: Long,
    val level: Double,
    val type: String,
    val advice: String,
    val createdAt: String
)