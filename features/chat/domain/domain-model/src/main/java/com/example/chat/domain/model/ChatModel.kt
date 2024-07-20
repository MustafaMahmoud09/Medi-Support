package com.example.chat.domain.model

data class ChatModel(
    val doctorName: String,
    val doctorId: Long,
    val doctorImageUrl: String,
    val lastMessage: String,
    val activeStatus: Boolean,
    val unseenCount: String,
    val lastMessageTime: String,
    val doctorJobType: String
)