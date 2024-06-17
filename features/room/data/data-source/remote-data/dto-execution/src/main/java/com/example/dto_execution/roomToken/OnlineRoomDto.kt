package com.example.dto_execution.roomToken

import com.example.room.domain.dto.declarations.roomToken.IOnlineRoomDto
import com.google.gson.annotations.SerializedName

data class OnlineRoomDto(
    @SerializedName("generate_token")
    override val generateToken: String?,
    @SerializedName("room_name")
    override val roomName: String?,
    @SerializedName("call_id")
    override val callId: Long?
): IOnlineRoomDto