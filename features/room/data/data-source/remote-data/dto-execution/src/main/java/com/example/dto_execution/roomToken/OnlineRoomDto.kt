package com.example.dto_execution.roomToken


import com.example.room.domain.dto.declarations.roomToken.IData
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("generate_token")
    override val generateToken: String?,
    @SerializedName("room_name")
    override val roomName: String?
): IData