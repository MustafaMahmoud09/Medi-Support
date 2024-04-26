package com.example.blood.pressure.data.source.remote.data.dto.execution.pageMeasurement


import com.example.blood.pressure.domain.dto.declarations.pageMeasurement.ILinks
import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("first")
    override val first: String?,
    @SerializedName("last")
    override val last: String?,
    @SerializedName("next")
    override val next: String?,
    @SerializedName("prev")
    override val prev: Any?
): ILinks