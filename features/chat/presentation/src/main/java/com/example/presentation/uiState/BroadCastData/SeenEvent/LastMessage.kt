package com.example.presentation.uiState.BroadCastData.SeenEvent


import com.google.gson.annotations.SerializedName

data class LastMessage(
    @SerializedName("lastMessage")
    val lastMessage: LastMessageX?,
    @SerializedName("unseenCounter")
    val unseenCounter: Int?,
    @SerializedName("user")
    val user: User?
)