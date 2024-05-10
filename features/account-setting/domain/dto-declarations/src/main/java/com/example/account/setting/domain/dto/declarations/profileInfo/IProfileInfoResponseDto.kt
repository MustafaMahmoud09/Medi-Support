package com.example.account.setting.data.source.remote.data.dto.execution.profileInfo


import com.google.gson.annotations.SerializedName

data class ProfileInfoResponseDto(
    @SerializedName("data")
    val `data`: ProfileInfoDto?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)