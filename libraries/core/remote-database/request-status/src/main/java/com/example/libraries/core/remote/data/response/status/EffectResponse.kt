package com.example.libraries.core.remote.data.response.status


data class Response<T>(
    val statusCode: Int,
    val body: T?
)