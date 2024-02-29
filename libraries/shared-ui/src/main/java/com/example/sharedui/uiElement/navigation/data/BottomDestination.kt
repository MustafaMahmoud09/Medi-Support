package com.example.sharedui.uiElement.navigation.data

data class BottomDestination(
    val route: String,
    val icon: Int,
    val title: Int,
    val childList: List<String>? = null
)
