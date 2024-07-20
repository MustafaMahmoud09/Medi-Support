package com.example.presentation.uiState.BroadCastData.MessageEvent


import com.google.gson.annotations.SerializedName


data class Attachment(
    @SerializedName("file")
    val `file`: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)