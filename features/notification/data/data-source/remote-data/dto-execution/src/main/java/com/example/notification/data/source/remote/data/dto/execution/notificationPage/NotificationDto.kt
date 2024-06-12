package com.example.notification.data.source.remote.data.dto.execution.notificationPage


import com.example.notification.domain.dto.declarations.notificationPage.INotificationDto
import com.google.gson.annotations.SerializedName

data class NotificationDto(
    @SerializedName("id")
    override val id: String?,
    @SerializedName("message")
    override val message: String?,
    @SerializedName("read_at")
    override val readAt: String?
): INotificationDto