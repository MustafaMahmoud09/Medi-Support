package com.example.onlinebooking.presentation.uiState.state

data class InfiniteGetterStatus<T>(
    val data: T?= null,
    val loading: Boolean = false
)

