package com.example.chat.data.source.remote.data.dto.execution.sendMessage


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("attachment")
    val attachment: Attachment?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("from_id")
    val fromId: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isSender")
    val isSender: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("seen")
    val seen: Any?,
    @SerializedName("timeAgo")
    val timeAgo: String?,
    @SerializedName("to_id")
    val toId: String?
)