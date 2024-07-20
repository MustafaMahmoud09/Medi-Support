package com.example.chat.data.source.remote.data.dto.execution.sendMessage


import com.google.gson.annotations.SerializedName

data class SendMessageResponseDto(
    @SerializedName("error")
    val error: Error?,
    @SerializedName("message")
    val message: Message?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tempID")
    val tempID: String?
)