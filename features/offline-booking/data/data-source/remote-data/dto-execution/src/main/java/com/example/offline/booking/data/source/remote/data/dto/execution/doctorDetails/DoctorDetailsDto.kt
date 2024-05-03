package com.example.offline.booking.data.source.remote.data.dto.execution.doctorDetails

import com.example.offline.booking.domain.dto.declarations.doctorDetails.IDoctorDetailsDto
import com.google.gson.annotations.SerializedName

data class DoctorDetailsDto(
    @SerializedName("Avatar")
    override val avatar: String?,
    @SerializedName("avg_rating")
    override val avgRating: Float?,
    @SerializedName("bio")
    override val bio: String?,
    @SerializedName("clinic_location")
    override val clinicLocation: String?,
    @SerializedName("dates")
    override val dates: List<Date?>?,
    @SerializedName("first_name")
    override val firstName: String?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("last_name")
    override val lastName: String?,
    @SerializedName("photo")
    override val photo: Any?,
    @SerializedName("price")
    override val price: Int?,
    @SerializedName("specialization")
    override val specialization: String?,
    @SerializedName("user_rating")
    override val userRating: Float?
): IDoctorDetailsDto