package com.example.chat.domain.dto.declarations.sendMessage


interface ISendMessageResponseDto {

    val error: IError?

    val message: IMessage?

    val status: String?

    val tempID: String?

}