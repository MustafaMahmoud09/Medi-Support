package com.example.auth.data.source.remote.data.requests

import retrofit2.http.Field
import retrofit2.http.POST

interface AuthRequest {

    /***
     * function for make register request on server
     ***/
    @POST("auth/user/register")
    fun register(
        @Field("name") name: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") confirmPassword: String
    )


    /***
     * function for make login with social media request on server
     ***/
    @POST("auth/user/social-login")
    fun loginWithSocial(
        @Field("access_provider_token") token: String,
        @Field("provider") provider: String
    )


    /***
     * function for make login with email request on server
     ***/
    @POST("auth/user/login")
    fun loginWithEmail(
        @Field("email") email: String,
        @Field("password") password: String
    )


}//end AuthRequest