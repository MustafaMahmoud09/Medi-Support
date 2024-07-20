package com.example.chat.data.source.remote.data.dto.execution.chatAuth


import com.google.gson.annotations.SerializedName

data class ChatAuthResponseDto(
    @SerializedName("auth")
    val auth: String?,
    @SerializedName("channel_data")
    val channelData: String?
)