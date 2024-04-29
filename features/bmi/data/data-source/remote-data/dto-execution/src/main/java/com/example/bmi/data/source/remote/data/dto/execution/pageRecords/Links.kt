package com.example.bmi.data.source.remote.data.dto.execution.pageRecords


import com.example.bmi.domain.dto.declarations.pageRecords.ILinks
import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("current_page")
    override val currentPage: Int?,
    @SerializedName("first_page_url")
    override val firstPageUrl: String?,
    @SerializedName("last_page")
    override val lastPage: Int?,
    @SerializedName("last_page_url")
    override val lastPageUrl: String?,
    @SerializedName("next_page_url")
    override val nextPageUrl: String?,
    @SerializedName("prev_page_url")
    override val prevPageUrl: Any?,
    @SerializedName("total")
    override val total: Int?
): ILinks