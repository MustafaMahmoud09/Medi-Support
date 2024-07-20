package com.example.presentation.uiState.BroadCastData.SeenEvent


import com.google.gson.annotations.SerializedName

data class LastMessageX(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("last_message")
    val lastMessage: String?,
    @SerializedName("timeAgo")
    val timeAgo: String?
)