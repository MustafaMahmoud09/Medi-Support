package com.example.chat.data.source.remote.data.dto.execution.chat


import com.example.chat.domain.dto.declarations.chat.IContactDto
import com.google.gson.annotations.SerializedName

data class ContactDto(
    @SerializedName("lastMessage")
    override val lastMessage: LastMessage?,
    @SerializedName("unseenCounter")
    override val unseenCounter: Int?,
    @SerializedName("user")
    override val user: User?
): IContactDto