package com.example.data.source.remote.data.dto.execution.response.emailUser


import com.example.auth.domain.dto.declarations.emailUser.IUser
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("active_status")
    override val activeStatus: Int?,
    @SerializedName("avatar")
    override val avatar: String?,
    @SerializedName("email")
    override val email: String?,
    @SerializedName("first_name")
    override val firstName: String?,
    @SerializedName("id")
    override val id: Int?,
    @SerializedName("last_name")
    override val lastName: String?
): IUser