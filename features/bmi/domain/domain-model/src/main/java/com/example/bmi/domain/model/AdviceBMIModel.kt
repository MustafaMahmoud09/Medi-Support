package com.example.heart.rate.domain.domain.model

data class AdviceHeartRateModel(
    val id: Long,
    val rate: Long,
    val type: String,
    val advice: String,
    val createdAt: String
)