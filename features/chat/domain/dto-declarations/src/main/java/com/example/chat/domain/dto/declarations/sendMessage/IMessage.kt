package com.example.chat.domain.dto.declarations.sendMessage

interface IMessage {

    val attachment: IAttachment?

    val createdAt: String?

    val fromId: Long?

    val id: String?

    val isSender: Boolean?

    val message: String?

    val seen: Any?

    val timeAgo: String?

    val toId: Long?

}