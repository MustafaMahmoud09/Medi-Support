package com.example.bmi.domain.model

data class AdviceBMIModel(
    val id: Long,
    val result: String,
    val type: String,
    val advice: String,
    val createdAt: String
)