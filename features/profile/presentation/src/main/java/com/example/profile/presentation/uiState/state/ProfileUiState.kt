package com.example.profile.presentation.uiState.state

import android.net.Uri

data class ProfileUiState(
    val imageUri: Uri = Uri.parse(""),
    val userName: String = "mustafa.15",
    val passwordValue: String = "",
    val passwordConfirmPassword: String = "",
    val firstNameValue: String = "",
    val lastNameValue: String = ""
)
