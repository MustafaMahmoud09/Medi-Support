package com.example.chat.data.source.remote.data.dto.execution.sendMessage


import com.example.chat.domain.dto.declarations.sendMessage.IError
import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("message")
    override val message: Any?,
    @SerializedName("status")
    override val status: Int?
): IError