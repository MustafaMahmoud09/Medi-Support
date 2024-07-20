package com.example.chat.data.source.remote.data.dto.execution.sendMessage


import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("file")
    val `file`: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)