package com.example.notification.domain.model

data class NotificationModel(
    val id: String,
    val notification: String,
    val read: Boolean,
    val type: String,
    val bookingId: Long
)