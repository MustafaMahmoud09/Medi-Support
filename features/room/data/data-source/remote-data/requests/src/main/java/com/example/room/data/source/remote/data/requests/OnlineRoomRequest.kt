package com.example.room.data.source.remote.data.requests

import com.example.dto_execution.roomToken.RoomTokenResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OnlineRoomRequest {

    //function for make request on server for get online room token
    @FormUrlEncoded
    @POST("auth/user/video-call/token")
    suspend fun getOnlineRoomToken(
        @Field("booking_id") bookingId: Long
    ): Response<RoomTokenResponseDto>


    //function for make request on server for start online room
    @FormUrlEncoded
    @POST("auth/user/video-call/start")
    suspend fun startOnlineRoom(
        @Field("call_id") callId: Long
    ): Response<Any>


    //function for make request on server for finish online room
    @FormUrlEncoded
    @POST("auth/user/video-call/end")
    suspend fun finishOnlineRoom(
        @Field("booking_id") bookingId: Long,
        @Field("call_id") callId: Long
    ): Response<Any>

}//end OnlineRoomRequest