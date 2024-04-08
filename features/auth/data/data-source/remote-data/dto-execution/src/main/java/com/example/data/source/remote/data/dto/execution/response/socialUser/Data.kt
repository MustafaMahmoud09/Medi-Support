package com.example.data.source.remote.data.dto.execution.response.socialUser


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User?
)