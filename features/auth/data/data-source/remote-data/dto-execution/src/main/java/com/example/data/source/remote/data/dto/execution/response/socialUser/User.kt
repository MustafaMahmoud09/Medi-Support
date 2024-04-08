package com.example.data.source.remote.data.dto.execution.response.socialUser


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("provider_name")
    val providerName: String?
)