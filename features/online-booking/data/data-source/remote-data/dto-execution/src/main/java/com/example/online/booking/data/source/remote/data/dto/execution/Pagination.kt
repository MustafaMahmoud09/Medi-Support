package com.example.online.booking.data.source.remote.data.dto.execution


import com.example.online.booking.domain.dto.declarations.pageOnlineDoctor.IPagination
import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("current_page")
    override val currentPage: Int?,
    @SerializedName("first_page_url")
    override val firstPageUrl: String?,
    @SerializedName("last_page")
    override val lastPage: Int?,
    @SerializedName("last_page_url")
    override val lastPageUrl: String?,
    @SerializedName("next_page_url")
    override val nextPageUrl: Any?,
    @SerializedName("prev_page_url")
    override val prevPageUrl: Any?,
    @SerializedName("total")
    override val total: Int?
): IPagination