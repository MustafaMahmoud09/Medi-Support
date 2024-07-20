package com.example.chat.domain.dto.declarations.message


interface IMessageResponseDto {

    val lastMessageId: String?

    val lastPage: Int?

    val messages: List<IMessageDto?>?

    val total: Int?

}//end IMessageResponseDto