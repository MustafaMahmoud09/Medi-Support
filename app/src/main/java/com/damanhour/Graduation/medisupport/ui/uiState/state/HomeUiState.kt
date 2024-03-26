package com.damanhour.Graduation.medisupport.ui.uiState.state


data class HomeUiState(
    val pagerStack: List<Int> = listOf(0),
    val focusOnSearch: Boolean = false,
    val searchKey: String = ""
)
