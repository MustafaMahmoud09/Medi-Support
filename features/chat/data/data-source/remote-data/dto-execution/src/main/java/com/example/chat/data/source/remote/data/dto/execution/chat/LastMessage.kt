package com.example.chat.data.source.remote.data.dto.execution.chat


import com.example.chat.domain.dto.declarations.chat.ILastMessage
import com.google.gson.annotations.SerializedName

data class LastMessage(
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("last_message")
    override val lastMessage: String?,
    @SerializedName("timeAgo")
    override val timeAgo: String?
): ILastMessage