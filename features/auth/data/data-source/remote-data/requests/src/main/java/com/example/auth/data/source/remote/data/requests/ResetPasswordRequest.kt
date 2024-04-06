package com.example.auth.data.source.remote.data.requests

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ResetPasswordRequest {

    /**
     * function for make send email request on server
     **/
    @FormUrlEncoded
    @POST("auth/user/forgot-password")
    suspend fun sendUserEmail(
        @Field("email") email: String
    ): Response<Any>

    /***
     * function for make verify code request on server
     **/
    @FormUrlEncoded
    @POST("auth/user/verfiy-code")
    suspend fun verifyCode(
        @Field("email") email: String,
        @Field("verification_code") code: String
    ): Response<Any>

    /**
     * function for make reset password request on server
     **/
    @FormUrlEncoded
    @POST("auth/user/reset-password")
    suspend fun resetPassword(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String
    ): Response<Any>

}//end NewPasswordRequest