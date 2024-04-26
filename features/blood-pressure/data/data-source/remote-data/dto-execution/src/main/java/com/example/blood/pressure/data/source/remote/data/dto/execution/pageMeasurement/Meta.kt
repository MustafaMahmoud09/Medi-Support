package com.example.blood.pressure.data.source.remote.data.dto.execution.pageMeasurement


import com.example.blood.pressure.domain.dto.declarations.pageMeasurement.IMeta
import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("current_page")
    override val currentPage: Int?,
    @SerializedName("from")
    override val from: Int?,
    @SerializedName("last_page")
    override val lastPage: Int?,
    @SerializedName("links")
    override val links: List<Link?>?,
    @SerializedName("path")
    override val path: String?,
    @SerializedName("per_page")
    override val perPage: Int?,
    @SerializedName("to")
    override val to: Int?,
    @SerializedName("total")
    override val total: Int?
): IMeta