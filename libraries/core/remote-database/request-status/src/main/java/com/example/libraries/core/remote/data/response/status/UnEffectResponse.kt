package com.example.libraries.core.remote.data.response.status


data class UnEffectResponse<T>(
    val lastPageNumber: Int,
    val body: T?
)