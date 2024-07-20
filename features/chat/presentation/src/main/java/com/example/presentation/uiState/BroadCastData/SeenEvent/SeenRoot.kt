package com.example.presentation.uiState.BroadCastData.SeenEvent


import com.google.gson.annotations.SerializedName

data class SeenRoot(
    @SerializedName("chat_id")
    val chatId: Long?,
    @SerializedName("last_message")
    val lastMessage: LastMessage?
)