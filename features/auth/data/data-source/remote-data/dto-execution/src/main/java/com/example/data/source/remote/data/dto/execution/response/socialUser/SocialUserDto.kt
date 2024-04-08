package com.example.data.source.remote.data.dto.execution.response.socialUser


import com.google.gson.annotations.SerializedName

data class SocialUser(
    @SerializedName("data")
    val data: Data?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)