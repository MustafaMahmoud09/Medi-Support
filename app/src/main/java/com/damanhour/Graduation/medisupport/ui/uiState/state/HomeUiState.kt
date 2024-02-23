package com.damanhour.Graduation.medisupport.ui.uiState.state


data class HomeUiState(
    val prevPage: Int = 0,
    val currentPage: Int = 0,
    val focusOnSearch: Boolean = false,
    val searchKey: String = ""
)
