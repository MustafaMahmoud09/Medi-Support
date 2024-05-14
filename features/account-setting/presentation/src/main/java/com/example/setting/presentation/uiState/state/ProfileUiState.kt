package com.example.setting.presentation.uiState.state

import android.net.Uri
import com.example.account.setting.domain.model.UserModel

data class ProfileUiState(
    val imageUri: Uri = Uri.parse(""),
    val errorTypeMimeSelected: Boolean = false,
    val startRunning: Boolean = true,
    val imageUpdated: Boolean = false,
    val imageUploaded: Boolean = false,
    val userName: String = "",
    val passwordValue: String = "",
    val passwordConfirmPassword: String = "",
    val firstNameValue: String = "",
    val lastNameValue: String = "",
    val userInfo: UserModel? = null,
    val updateProfileEventStatus: UpdateProfileEventStatus = UpdateProfileEventStatus()
)


data class UpdateProfileEventStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val dataNotFound: Boolean = false,
    val dataError: Boolean = false
)
