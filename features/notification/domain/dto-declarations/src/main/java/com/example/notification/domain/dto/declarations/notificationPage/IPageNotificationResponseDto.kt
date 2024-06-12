package com.example.notification.data.source.remote.data.dto.execution.notificationPage


import com.google.gson.annotations.SerializedName

data class PageNotificationResponseDto(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)