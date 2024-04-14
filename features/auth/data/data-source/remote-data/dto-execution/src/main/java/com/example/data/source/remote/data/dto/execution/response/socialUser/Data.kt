package com.example.data.source.remote.data.dto.execution.response.socialUser


import com.example.auth.domain.dto.declarations.socialUser.IData
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("token")
    override val token: String?,
    @SerializedName("user")
    override val user: User?
): IData