package com.example.chat.data.source.remote.data.dto.execution.message


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("attachment")
    val attachment: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("from_id")
    val fromId: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("seen")
    val seen: Int?,
    @SerializedName("to_id")
    val toId: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?
)