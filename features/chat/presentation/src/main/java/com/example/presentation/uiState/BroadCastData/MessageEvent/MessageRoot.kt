package com.example.presentation.uiState.BroadCastData.MessageEvent

import com.google.gson.annotations.SerializedName

data class Root(
    @SerializedName("from_id")
    val fromId: Long?,
    @SerializedName("message")
    val message: Message?,
    @SerializedName("to_id")
    val toId: Long?
)