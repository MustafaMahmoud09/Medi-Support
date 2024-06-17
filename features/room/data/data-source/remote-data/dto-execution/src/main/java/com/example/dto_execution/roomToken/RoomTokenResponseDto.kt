package com.example.dto_execution.roomToken


import com.example.room.domain.dto.declarations.roomToken.IRoomTokenResponseDto
import com.google.gson.annotations.SerializedName

data class RoomTokenResponseDto(
    @SerializedName("data")
    override val `data`: OnlineRoomDto?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IRoomTokenResponseDto