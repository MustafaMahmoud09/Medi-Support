package com.example.chat.data.source.remote.data.dto.execution.chatAuth


import com.example.chat.domain.dto.declarations.chatAuth.IChatAuthResponseDto
import com.google.gson.annotations.SerializedName

data class ChatAuthResponseDto(
    @SerializedName("auth")
    override val auth: String?,
    @SerializedName("channel_data")
    override val channelData: String?
): IChatAuthResponseDto