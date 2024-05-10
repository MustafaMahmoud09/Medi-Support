package com.example.account.setting.data.source.remote.data.requests

import com.example.account.setting.data.source.remote.data.dto.execution.profileInfo.ProfileInfoResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface AccountSettingRequest {

    /**
     * function for make request on server for update profile
     **/
    @Multipart
    @POST("auth/user/update-profile")
    @JvmSuppressWildcards
    suspend fun updateProfile(
        @PartMap params: Map<String, RequestBody>,
        @Part avatar: MultipartBody.Part?
    ): Response<Any>


    /**
     * function for make request on server for get profile info
     **/
    @GET("auth/user/user-profile")
    suspend fun getProfileInfo(): Response<ProfileInfoResponseDto>


    /**
     * function for make request on server for send contact us message to admin
     **/
    @FormUrlEncoded
    @POST("contact")
    suspend fun sendContactUsMessage(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("message") message: String
    ): Response<Any>


    /**
     * function for make request on server for delete user account
     **/
    @DELETE("auth/user/delete-account")
    suspend fun deleteUserAccount(): Response<Any>


    /**
     * function for make request on server for make logout
     **/
    @POST("auth/user/logout")
    suspend fun logout(): Response<Any>

}//end AccountSettingRequest