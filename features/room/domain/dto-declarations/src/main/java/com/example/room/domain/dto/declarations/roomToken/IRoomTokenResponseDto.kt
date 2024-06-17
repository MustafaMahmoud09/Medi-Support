package com.example.dto_execution.roomToken


import com.google.gson.annotations.SerializedName

data class RoomTokenResponseDto(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)