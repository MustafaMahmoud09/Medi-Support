package com.example.chat.data.source.remote.data.dto.execution.sendMessage


import com.example.chat.domain.dto.declarations.sendMessage.IAttachment
import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("file")
    override val `file`: String?,
    @SerializedName("title")
    override val title: String?,
    @SerializedName("type")
    override val type: String?
): IAttachment