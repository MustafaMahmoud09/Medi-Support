package com.example.offline.booking.data.source.remote.data.dto.execution.doctorDetails


import com.google.gson.annotations.SerializedName

data class DoctorDetailsDto(
    @SerializedName("Avatar")
    val avatar: String?,
    @SerializedName("avg_rating")
    val avgRating: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("clinic_location")
    val clinicLocation: String?,
    @SerializedName("dates")
    val dates: List<Date?>?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("photo")
    val photo: Any?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("specialization")
    val specialization: String?,
    @SerializedName("user_rating")
    val userRating: Any?
)