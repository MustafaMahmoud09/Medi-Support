package com.example.data.source.remote.data.dto.execution.response

import com.example.auth.domain.dto.declarations.IResponse

data class Response<T>(
    override val statusCode: Int,
    override val body: T?
) : IResponse<T>