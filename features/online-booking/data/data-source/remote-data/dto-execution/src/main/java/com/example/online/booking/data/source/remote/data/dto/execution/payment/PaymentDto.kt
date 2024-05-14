package com.example.online.booking.data.source.remote.data.dto.execution.payment


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("client_secret")
    val clientSecret: String?,
    @SerializedName("customer")
    val customer: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("metadata")
    val metadata: Metadata?
)