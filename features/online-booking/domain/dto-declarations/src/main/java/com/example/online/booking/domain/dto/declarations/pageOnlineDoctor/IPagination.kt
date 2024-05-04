package com.example.online.booking.data.source.remote.data.dto.execution.pageOnlineDoctor


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("first_page_url")
    val firstPageUrl: String?,
    @SerializedName("last_page")
    val lastPage: Int?,
    @SerializedName("last_page_url")
    val lastPageUrl: String?,
    @SerializedName("next_page_url")
    val nextPageUrl: Any?,
    @SerializedName("prev_page_url")
    val prevPageUrl: Any?,
    @SerializedName("total")
    val total: Int?
)