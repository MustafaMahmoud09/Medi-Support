package com.example.chat.data.source.remote.data.dto.execution.message

import com.example.chat.domain.dto.declarations.message.IMessageResponseDto
import com.google.gson.annotations.SerializedName

data class MessageResponseDto(
    @SerializedName("last_message_id")
    override val lastMessageId: String?,
    @SerializedName("last_page")
    override val lastPage: Int?,
    @SerializedName("messages")
    override val messages: List<MessageDto?>?,
    @SerializedName("total")
    override val total: Int?
): IMessageResponseDto