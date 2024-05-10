package com.example.account.setting.data.source.remote.data.dto.execution.profileInfo

import com.example.account.setting.domain.dto.declarations.profileInfo.IProfileInfoResponseDto
import com.google.gson.annotations.SerializedName

data class ProfileInfoResponseDto(
    @SerializedName("data")
    override val `data`: ProfileInfoDto?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IProfileInfoResponseDto