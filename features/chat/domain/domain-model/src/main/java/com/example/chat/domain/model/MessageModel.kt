package com.example.chat.domain.model


data class MessageModel(
    val messageId: String,
    val fromId: Long,
    val toId: Long,
    val body: String,
    val attachment: AttachmentModel,
    val seen: Boolean,
    val time: String,
    val isStartMessage: Boolean = false,
    val isEndMessage: Boolean = false,
    val localId: String = "",
)
