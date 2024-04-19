package com.example.libraries.core.remote.data.response.status


data class EffectResponse<T>(
    val statusCode: Int,
    val body: T?
)