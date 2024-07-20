package com.example.chat.data.source.remote.data.dto.execution.sendMessage


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("message")
    val message: Any?,
    @SerializedName("status")
    val status: Int?
)