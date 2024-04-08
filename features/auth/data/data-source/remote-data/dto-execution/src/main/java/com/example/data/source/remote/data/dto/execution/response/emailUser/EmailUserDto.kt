package com.example.data.source.remote.data.dto.execution.response.emailUser


import com.google.gson.annotations.SerializedName

data class EmailUserDto(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("user")
    val user: User?
)