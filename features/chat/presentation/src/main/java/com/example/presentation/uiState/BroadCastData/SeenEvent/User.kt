package com.example.presentation.uiState.BroadCastData.SeenEvent


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("active_status")
    val activeStatus: Int?,
    @SerializedName("admin_id")
    val adminId: Int?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("clinic_location")
    val clinicLocation: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("specialization")
    val specialization: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("working_hours")
    val workingHours: String?
)