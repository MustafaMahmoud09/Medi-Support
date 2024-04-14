package com.example.data.source.remote.data.dto.execution.response.socialUser


import com.example.auth.domain.dto.declarations.socialUser.ISocialUserDto
import com.google.gson.annotations.SerializedName

data class SocialUserDto(
    @SerializedName("data")
    override val data: Data?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
) : ISocialUserDto