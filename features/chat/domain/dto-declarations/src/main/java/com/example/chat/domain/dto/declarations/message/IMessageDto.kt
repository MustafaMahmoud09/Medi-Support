package com.example.chat.domain.dto.declarations.message

interface IMessageDto {

    val attachment: String?

    val body: String?

    val createdAt: String?

    val fromId: Long?

    val id: String?

    val seen: Int?

    val toId: Long?

    val updatedAt: String?

}//end IMessageDto