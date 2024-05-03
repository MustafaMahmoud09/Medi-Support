package com.example.offline.booking.data.source.remote.data.dto.execution


import com.example.offline.booking.domain.dto.declarations.IOfflineDoctorDto
import com.google.gson.annotations.SerializedName

data class OfflineDoctorDto(
    @SerializedName("Avatar")
    override val avatar: String?,
    @SerializedName("clinic_location")
    override val clinicLocation: String?,
    @SerializedName("first_name")
    override val firstName: String?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("last_name")
    override val lastName: String?,
    @SerializedName("rate")
    override val rate: Float?,
    @SerializedName("working_hours")
    override val workingHours: Int?
): IOfflineDoctorDto