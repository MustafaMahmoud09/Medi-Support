package com.example.online.booking.data.source.remote.data.dto.execution.payment


import com.example.online.booking.domain.dto.declarations.payment.IPaymentDto
import com.google.gson.annotations.SerializedName

data class PaymentDto(
    @SerializedName("client_secret")
    override val clientSecret: String?,
    @SerializedName("customer")
    override val customer: String?,
    @SerializedName("id")
    override val id: String?,
    @SerializedName("metadata")
    override val metadata: Metadata?
): IPaymentDto