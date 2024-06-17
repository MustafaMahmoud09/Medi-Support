package com.example.room.domain.dto.declarations.roomToken

interface IRoomTokenResponseDto {

    val `data`: IOnlineRoomDto?

    val error: Boolean?

    val message: String?

}//end IRoomTokenResponseDto