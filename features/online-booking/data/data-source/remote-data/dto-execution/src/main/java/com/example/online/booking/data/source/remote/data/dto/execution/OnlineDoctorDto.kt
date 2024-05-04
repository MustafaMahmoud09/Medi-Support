package com.example.online.booking.data.source.remote.data.dto.execution


import com.example.online.booking.domain.dto.declarations.IOnlineDoctorDto
import com.google.gson.annotations.SerializedName

data class OnlineDoctorDto(
    @SerializedName("active_status")
    override val activeStatus: Int?,
    @SerializedName("admin_id")
    override val adminId: Long?,
    @SerializedName("avatar")
    override val avatar: String?,
    @SerializedName("average_rating")
    override val averageRating: Float?,
    @SerializedName("bio")
    override val bio: String?,
    @SerializedName("clinic_location")
    override val clinicLocation: String?,
    @SerializedName("email")
    override val email: String?,
    @SerializedName("first_name")
    override val firstName: String?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("last_name")
    override val lastName: String?,
    @SerializedName("phone")
    override val phone: String?,
    @SerializedName("price")
    override val price: Int?,
    @SerializedName("specialization")
    override val specialization: String?,
    @SerializedName("working_hours")
    override val workingHours: Int?
): IOnlineDoctorDto