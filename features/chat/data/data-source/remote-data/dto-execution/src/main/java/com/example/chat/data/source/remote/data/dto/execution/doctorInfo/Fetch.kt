package com.example.chat.data.source.remote.data.dto.execution.doctorInfo


import com.example.chat.domain.dto.declarations.doctorInfo.IFetch
import com.google.gson.annotations.SerializedName

data class Fetch(
    @SerializedName("active_status")
    override val activeStatus: Int?,
    @SerializedName("admin_id")
    override val adminId: Int?,
    @SerializedName("avatar")
    override val avatar: String?,
    @SerializedName("bio")
    override val bio: String?,
    @SerializedName("clinic_location")
    override val clinicLocation: String?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("email")
    override val email: String?,
    @SerializedName("first_name")
    override val firstName: String?,
    @SerializedName("id")
    override val id: Int?,
    @SerializedName("last_name")
    override val lastName: String?,
    @SerializedName("phone")
    override val phone: String?,
    @SerializedName("price")
    override val price: Int?,
    @SerializedName("specialization")
    override val specialization: String?,
    @SerializedName("updated_at")
    override val updatedAt: String?,
    @SerializedName("working_hours")
    override val workingHours: String?
): IFetch