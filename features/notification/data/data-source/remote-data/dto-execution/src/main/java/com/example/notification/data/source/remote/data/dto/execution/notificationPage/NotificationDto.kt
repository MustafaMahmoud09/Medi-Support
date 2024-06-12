package com.example.notification.data.source.remote.data.dto.execution.notificationPage


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("id")
    val id: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("read_at")
    val readAt: String?
)