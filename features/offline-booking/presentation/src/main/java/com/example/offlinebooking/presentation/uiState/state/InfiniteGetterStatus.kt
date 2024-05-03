package com.example.offlinebooking.presentation.uiState.state

data class InfiniteGetterStatus<T>(
    val data: T?= null,
    val loading: Boolean = false
)

