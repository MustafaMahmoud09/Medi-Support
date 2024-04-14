package com.example.data.source.remote.data.dto.execution.response.socialUser


import com.example.auth.domain.dto.declarations.socialUser.IUser
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    override val email: String?,
    @SerializedName("first_name")
    override val firstName: String?,
    @SerializedName("last_name")
    override val lastName: String?,
    @SerializedName("provider_name")
    override val providerName: String?
): IUser