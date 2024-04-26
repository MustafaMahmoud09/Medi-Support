package com.example.blood.pressure.data.source.remote.data.dto.execution.pageMeasurement


import com.example.blood.pressure.domain.dto.declarations.pageMeasurement.ILink
import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("active")
    override val active: Boolean?,
    @SerializedName("label")
    override val label: String?,
    @SerializedName("url")
    override val url: String?
): ILink