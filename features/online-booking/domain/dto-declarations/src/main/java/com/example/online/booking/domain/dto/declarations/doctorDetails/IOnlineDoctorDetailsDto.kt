package com.example.online.booking.data.source.remote.data.dto.execution.doctorDetails


import com.google.gson.annotations.SerializedName

data class OnlineDoctorDetailsDto(
    @SerializedName("active_status")
    val activeStatus: Int?,
    @SerializedName("admin_id")
    val adminId: Int?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("average_rating")
    val averageRating: Int?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("clinic_location")
    val clinicLocation: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("specialization")
    val specialization: String?,
    @SerializedName("working_hours")
    val workingHours: String?
)