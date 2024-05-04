package com.example.online.booking.data.source.remote.data.dto.execution.pageOnlineDoctor

import com.example.online.booking.domain.dto.declarations.pageOnlineDoctor.IPageOnlineDoctorResponseDto
import com.google.gson.annotations.SerializedName

data class PageOnlineDoctorResponseDto(
    @SerializedName("data")
    override val `data`: Data?,
    @SerializedName("error")
    override val error: Boolean?,
    @SerializedName("message")
    override val message: String?
): IPageOnlineDoctorResponseDto