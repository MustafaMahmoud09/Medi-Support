package com.example.data.source.remote.data.dto.execution.response.emailUser


import com.example.auth.domain.dto.declarations.emailUser.IEmailUserDto
import com.google.gson.annotations.SerializedName

data class EmailUserDto(
    @SerializedName("access_token")
    override val accessToken: String?,
    @SerializedName("token_type")
    override val tokenType: String?,
    @SerializedName("user")
    override val user: User?
) : IEmailUserDto