package com.example.booking.uiState.state.doctors


data class HomeUiState(
    val prevPage: Int = 0,
    val currentPage: Int = 0,
    val focusOnSearch: Boolean = false,
    val searchKey: String = ""
)
