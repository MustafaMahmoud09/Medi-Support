package com.example.auth.data.source.remote.data.requests

import com.example.data.source.remote.data.dto.execution.response.emailUser.EmailUserDto
import com.example.data.source.remote.data.dto.execution.response.socialUser.SocialUserDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthRequest {

    /***
     * function for make register request on server
     ***/
    @FormUrlEncoded
    @POST("auth/user/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String
    ): Response<Any>


    /***
     * function for make login with social media request on server
     ***/
    @FormUrlEncoded
    @POST("auth/user/social-login")
    suspend fun loginWithSocial(
        @Field("access_provider_token") token: String,
        @Field("provider") provider: String
    ): Response<SocialUserDto>


    /***
     * function for make login with email request on server
     ***/
    @FormUrlEncoded
    @POST("auth/user/login")
    suspend fun loginWithEmail(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<EmailUserDto>


}//end AuthRequest
