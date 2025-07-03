package com.example.chat.data.source.remote.data.dto.execution.chat


import com.example.chat.domain.dto.declarations.chat.IUser
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("active_status")
    override val activeStatus: Int?,
    @SerializedName("avatar")
    override val avatar: String?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("email")
    override val email: String?,
    @SerializedName("first_name")
    override val firstName: String?,
    @SerializedName("specialization")
    override val specialization: String?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("last_name")
    override val lastName: String?,
    @SerializedName("max_created_at")
    override val maxCreatedAt: String?,
    @SerializedName("updated_at")
    override val updatedAt: String?
): IUser