package com.example.chat.data.source.remote.data.dto.execution.doctorInfo


import com.google.gson.annotations.SerializedName

data class DoctorInfoResponseDto(
    @SerializedName("fetch")
    override val fetch: Fetch?
): IDoctorInfoResponseDto