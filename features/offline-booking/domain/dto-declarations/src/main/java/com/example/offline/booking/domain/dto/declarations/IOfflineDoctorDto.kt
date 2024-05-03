package com.example.offline.booking.data.source.remote.data.dto.execution


import com.google.gson.annotations.SerializedName

data class OfflineDoctorDto(
    @SerializedName("Avatar")
    val avatar: String?,
    @SerializedName("clinic_location")
    val clinicLocation: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("rate")
    val rate: String?,
    @SerializedName("working_hours")
    val workingHours: String?
)