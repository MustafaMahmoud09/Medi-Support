package com.example.chat.data.source.remote.data.dto.execution.chat


import com.google.gson.annotations.SerializedName

data class ChatResponseDto(
    @SerializedName("contacts")
    val contactDtos: List<ContactDto?>?,
    @SerializedName("last_page")
    val lastPage: Int?,
    @SerializedName("total")
    val total: Int?
)