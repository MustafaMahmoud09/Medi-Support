package com.example.chat.data.source.remote.data.dto.execution.chat


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("active_status")
    val activeStatus: Int?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("max_created_at")
    val maxCreatedAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)