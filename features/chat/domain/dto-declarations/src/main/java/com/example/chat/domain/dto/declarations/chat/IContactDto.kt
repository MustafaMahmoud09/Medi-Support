package com.example.chat.domain.dto.declarations.chat


interface IContactDto {

    val lastMessage: ILastMessage?

    val unseenCounter: Int?

    val user: IUser?

}//end IContactDto