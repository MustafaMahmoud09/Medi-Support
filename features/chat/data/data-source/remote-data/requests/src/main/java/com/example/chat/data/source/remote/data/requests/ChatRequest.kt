package com.example.chat.data.source.remote.data.requests

import com.example.chat.data.source.remote.data.dto.execution.chat.ChatResponseDto
import com.example.chat.data.source.remote.data.dto.execution.chatAuth.ChatAuthResponseDto
import com.example.chat.data.source.remote.data.dto.execution.doctorInfo.DoctorInfoResponseDto
import com.example.chat.data.source.remote.data.dto.execution.message.MessageResponseDto
import com.example.chat.data.source.remote.data.dto.execution.sendMessage.SendMessageResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Query

interface ChatRequest {


    /**
     * function for make request on server for get page contain on user contacts
     **/
    @GET("user/chat/getUserContacts")
    suspend fun getPageChats(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ChatResponseDto>


    /**
     * function for make request on server for get page contain on user contacts
     **/
    @FormUrlEncoded
    @POST("user/chat/fetchMessages")
    suspend fun getChatMessages(
        @Field("page") page: Int,
        @Field("per_page") perPage: Int,
        @Field("id") doctorId: Int
    ): Response<MessageResponseDto>


    /**
     * function for make request on server for get page contain on user contacts
     **/
    @FormUrlEncoded
    @POST("user/chat/idInfo")
    suspend fun getDoctorInfoById(
        @Field("id") doctorId: Int
    ): Response<DoctorInfoResponseDto>


    /**
     * function for make request on server for get page contain on user contacts
     **/
    @FormUrlEncoded
    @POST("user/chat/auth")
    suspend fun authInChatChannel(
        @Field("socket_id") socketId: String,
        @Field("channel_name") channelName: String
    ): Response<ChatAuthResponseDto>


    /**
     * function for make request on server for get page contain on user contacts
     **/
    @FormUrlEncoded
    @POST("user/chat/makeSeen")
    suspend fun seenChatMessages(
        @Field("id") doctorId: Int
    ): Response<Any>


    /**
     * function for make request on server for get page contain on user contacts
     **/
    @Multipart
    @POST("user/chat/sendMessage")
    @JvmSuppressWildcards
    suspend fun sendMessage(
        @PartMap params: Map<String, RequestBody>,
        @Part file: MultipartBody.Part?
    ): Response<SendMessageResponseDto>


}//end ChatRequest