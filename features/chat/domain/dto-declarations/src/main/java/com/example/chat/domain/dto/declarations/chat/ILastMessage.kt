package com.example.chat.domain.dto.declarations.chat

interface ILastMessage {

    val createdAt: String?

    val lastMessage: String?

    val timeAgo: String?

}//end ILastMessage