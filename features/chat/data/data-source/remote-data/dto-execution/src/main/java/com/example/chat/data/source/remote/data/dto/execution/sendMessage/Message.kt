package com.example.chat.data.source.remote.data.dto.execution.sendMessage


import com.example.chat.domain.dto.declarations.sendMessage.IMessage
import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("attachment")
    override val attachment: Attachment?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("from_id")
    override val fromId: Long?,
    @SerializedName("id")
    override val id: String?,
    @SerializedName("isSender")
    override val isSender: Boolean?,
    @SerializedName("message")
    override val message: String?,
    @SerializedName("seen")
    override val seen: Any?,
    @SerializedName("timeAgo")
    override val timeAgo: String?,
    @SerializedName("to_id")
    override val toId: Long?
): IMessage