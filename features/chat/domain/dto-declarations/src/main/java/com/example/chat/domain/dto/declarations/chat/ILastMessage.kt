package com.example.chat.data.source.remote.data.dto.execution.chat


import com.google.gson.annotations.SerializedName

data class LastMessage(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("last_message")
    val lastMessage: String?,
    @SerializedName("timeAgo")
    val timeAgo: String?
)