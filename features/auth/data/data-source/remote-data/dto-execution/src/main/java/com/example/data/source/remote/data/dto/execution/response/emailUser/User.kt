package com.example.data.source.remote.data.dto.execution.response.emailUser


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("active_status")
    val activeStatus: Int?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?
)