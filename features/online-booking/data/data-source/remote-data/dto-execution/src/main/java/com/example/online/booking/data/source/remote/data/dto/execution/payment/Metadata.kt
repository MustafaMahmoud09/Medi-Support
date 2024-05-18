package com.example.online.booking.data.source.remote.data.dto.execution.payment


import com.example.online.booking.domain.dto.declarations.payment.IMetadata
import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("booking_id")
    override val bookingId: Long?,
    @SerializedName("doctor_id")
    override val doctorId: String?,
    @SerializedName("user_id")
    override val userId: String?
): IMetadata