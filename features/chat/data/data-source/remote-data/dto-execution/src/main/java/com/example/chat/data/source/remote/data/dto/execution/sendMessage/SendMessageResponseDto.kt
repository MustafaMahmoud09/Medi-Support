package com.example.chat.data.source.remote.data.dto.execution.sendMessage


import com.example.chat.domain.dto.declarations.sendMessage.ISendMessageResponseDto
import com.google.gson.annotations.SerializedName

data class SendMessageResponseDto(
    @SerializedName("error")
    override val error: Error?,
    @SerializedName("message")
    override val message: Message?,
    @SerializedName("status")
    override val status: String?,
    @SerializedName("tempID")
    override val tempID: String?
): ISendMessageResponseDto