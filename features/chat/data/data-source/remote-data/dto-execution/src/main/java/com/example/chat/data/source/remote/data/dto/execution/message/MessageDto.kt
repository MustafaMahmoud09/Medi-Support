package com.example.chat.data.source.remote.data.dto.execution.message


import com.example.chat.domain.dto.declarations.message.IMessageDto
import com.google.gson.annotations.SerializedName

data class MessageDto(
    @SerializedName("attachment")
    override val attachment: String?,
    @SerializedName("body")
    override val body: String?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("from_id")
    override val fromId: Long?,
    @SerializedName("id")
    override val id: String?,
    @SerializedName("seen")
    override val seen: Int?,
    @SerializedName("to_id")
    override val toId: Long?,
    @SerializedName("updated_at")
    override val updatedAt: String?
): IMessageDto