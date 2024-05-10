package com.example.account.setting.data.source.remote.data.dto.execution.profileInfo


import com.example.account.setting.domain.dto.declarations.profileInfo.IProfileInfoDto
import com.google.gson.annotations.SerializedName

data class ProfileInfoDto(
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
): IProfileInfoDto