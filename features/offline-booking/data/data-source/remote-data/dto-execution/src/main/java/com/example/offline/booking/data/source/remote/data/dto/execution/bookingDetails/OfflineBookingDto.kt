package com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails


import com.example.offline.booking.domain.dto.declarations.bookingDetails.IOfflineBookingDto
import com.google.gson.annotations.SerializedName

data class OfflineBookingDto(
    @SerializedName("clinic_location")
    override val clinicLocation: String?,
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("date")
    override val date: String?,
    @SerializedName("first_name")
    override val firstName: String?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("doctor_id")
    override val doctorId: Long?,
    @SerializedName("user_id")
    override val userId: Long?,
    @SerializedName("last_name")
    override val lastName: String?,
    @SerializedName("specialization")
    override val specialization: String?,
    @SerializedName("time")
    override val time: String?
): IOfflineBookingDto