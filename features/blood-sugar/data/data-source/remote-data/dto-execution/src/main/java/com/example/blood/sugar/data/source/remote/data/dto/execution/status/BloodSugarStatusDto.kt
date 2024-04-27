package com.example.blood.sugar.data.source.remote.data.dto.execution.status


import com.example.blood.sugar.domain.dto.declarations.status.IBloodSugarStatusDto
import com.google.gson.annotations.SerializedName

data class BloodSugarStatusDto(
    @SerializedName("id")
    override val id: Int?,
    @SerializedName("status-name")
    override val statusName: String?
): IBloodSugarStatusDto