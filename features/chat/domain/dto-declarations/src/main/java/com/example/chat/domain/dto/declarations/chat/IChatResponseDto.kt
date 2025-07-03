package com.example.chat.domain.dto.declarations.chat


interface IChatResponseDto {

    val contactDto: List<IContactDto?>?

    val lastPage: Int?

    val total: Int?

}//end IChatResponseDto