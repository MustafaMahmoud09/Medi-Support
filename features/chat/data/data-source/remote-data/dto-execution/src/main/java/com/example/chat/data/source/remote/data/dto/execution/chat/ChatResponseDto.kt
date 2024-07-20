package com.example.chat.data.source.remote.data.dto.execution.chat


import com.example.chat.domain.dto.declarations.chat.IChatResponseDto
import com.google.gson.annotations.SerializedName

data class ChatResponseDto(
    @SerializedName("contacts")
    override val contactDto: List<ContactDto?>?,
    @SerializedName("last_page")
    override val lastPage: Int?,
    @SerializedName("total")
    override val total: Int?
): IChatResponseDto