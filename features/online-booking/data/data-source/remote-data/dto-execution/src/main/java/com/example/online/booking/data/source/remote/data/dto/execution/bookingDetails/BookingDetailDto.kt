package com.example.online.booking.data.source.remote.data.dto.execution.bookingDetails

import com.example.online.booking.domain.dto.declarations.bookingDetails.IBookingDetailDto
import com.google.gson.annotations.SerializedName

data class BookingDetailDto(
    @SerializedName("created_at")
    override val createdAt: String?,
    @SerializedName("doctor_name")
    override val doctorName: String?,
    @SerializedName("id")
    override val id: Long?,
    @SerializedName("user_id")
    override val userId: Long?,
    @SerializedName("status")
    override val status: Int?,
    @SerializedName("username")
    override val username: String?,
    @SerializedName("specialization")
    override val specialization: String?,
    @SerializedName("active_status")
    override val activeStatus: Int?,
): IBookingDetailDto