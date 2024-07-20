package com.example.chat.data.source.remote.data.dto.execution.chat


import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("lastMessage")
    val lastMessage: LastMessage?,
    @SerializedName("unseenCounter")
    val unseenCounter: Int?,
    @SerializedName("user")
    val user: User?
)