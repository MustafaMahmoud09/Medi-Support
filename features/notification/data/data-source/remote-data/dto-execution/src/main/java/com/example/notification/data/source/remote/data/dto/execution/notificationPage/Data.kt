package com.example.notification.data.source.remote.data.dto.execution.notificationPage


import com.example.notification.domain.dto.declarations.notificationPage.IData
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("current_page")
    override val currentPage: Int?,
    @SerializedName("data")
    override val `data`: List<NotificationDto>?,
    @SerializedName("first_page_url")
    override val firstPageUrl: String?,
    @SerializedName("from")
    override val from: Int?,
    @SerializedName("last_page")
    override val lastPage: Int?,
    @SerializedName("last_page_url")
    override val lastPageUrl: String?,
    @SerializedName("next_page_url")
    override val nextPageUrl: Any?,
    @SerializedName("path")
    override val path: String?,
    @SerializedName("per_page")
    override val perPage: Int?,
    @SerializedName("prev_page_url")
    override val prevPageUrl: Any?,
    @SerializedName("to")
    override val to: Int?,
    @SerializedName("total")
    override val total: Int?
): IData