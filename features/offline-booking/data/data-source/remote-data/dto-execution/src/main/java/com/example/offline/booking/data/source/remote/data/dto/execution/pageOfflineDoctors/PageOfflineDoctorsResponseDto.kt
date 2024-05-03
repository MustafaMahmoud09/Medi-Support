package com.example.offline.booking.data.source.remote.data.dto.execution.pageOfflineDoctors


import com.example.offline.booking.domain.dto.declarations.pageOfflineDoctors.IPageOfflineDoctorsResponseDto
import com.google.gson.annotations.SerializedName

data class PageOfflineDoctorsResponseDto(
    @SerializedName("data")
    override val `data`: Data?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IPageOfflineDoctorsResponseDto