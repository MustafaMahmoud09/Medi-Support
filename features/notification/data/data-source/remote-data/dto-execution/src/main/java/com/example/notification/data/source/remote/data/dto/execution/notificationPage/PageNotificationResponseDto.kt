package com.example.notification.data.source.remote.data.dto.execution.notificationPage


import com.example.notification.domain.dto.declarations.notificationPage.IPageNotificationResponseDto
import com.google.gson.annotations.SerializedName

data class PageNotificationResponseDto(
    @SerializedName("data")
    override val `data`: Data?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IPageNotificationResponseDto