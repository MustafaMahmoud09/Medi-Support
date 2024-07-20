package com.example.chat.data.source.remote.data.dto.execution.message


import com.google.gson.annotations.SerializedName

data class MessageResponseDto(
    @SerializedName("last_message_id")
    val lastMessageId: String?,
    @SerializedName("last_page")
    val lastPage: Int?,
    @SerializedName("messages")
    val messages: List<MessageDto?>?,
    @SerializedName("total")
    val total: Int?
)