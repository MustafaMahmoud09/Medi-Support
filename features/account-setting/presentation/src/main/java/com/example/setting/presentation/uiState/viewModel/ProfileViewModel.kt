package com.example.setting.presentation.uiState.viewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.account.setting.domain.usecase.declarations.IGetProfileInfoUseCase
import com.example.account.setting.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase
import com.example.account.setting.domain.usecase.declarations.IUpdateProfileInfoUseCase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.setting.presentation.uiState.state.ProfileUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val updateProfileInfoUseCase: IUpdateProfileInfoUseCase,
    private val getProfileInfoUseCase: IGetProfileInfoUseCase,
    private val logoutFromLocalDatabaseUseCase: ILogoutFromLocalDatabaseUseCase,
    private val context: Context
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ProfileUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(250)
            _state.update {
                it.copy(
                    startRunning = false
                )
            }//end update
        }//end viewModelScope
        onGetUserInfo()
    }//end init

    //function for get user info
    private fun onGetUserInfo() {

        //create coroutine builder for call suspend function in it
        viewModelScope.launch(Dispatchers.IO) {

            getProfileInfoUseCase().collectLatest { userModels ->

                _state.update {
                    it.copy(
                        userInfo = if (userModels.isNotEmpty()) userModels[0] else null
                    )
                }//end update

            }//end collectLatest

        }//end coroutine builder scope

    }//end onGetUserInfo


    //function for update image uri
    fun onImageUriChanged(uri: Uri?) {

        //check uri not equal null
        if (uri != null) {

            val mimeType = context.contentResolver.getType(uri)

            Log.d("MIMES", mimeType.toString())

            if (
                mimeType == "image/jpg" ||
                mimeType == "image/png" ||
                mimeType == "image/jpeg"
            ) {

                Log.d("Tag", "file path ${uri.path}")

                //update image uri state here
                _state.update {
                    it.copy(
                        imageUri = uri,
                        imageUpdated = true,
                        imageUploaded = true
                    )
                }//end update

            }//end if
            else {

                _state.update {
                    it.copy(
                        errorTypeMimeSelected = !state.value.errorTypeMimeSelected
                    )
                }//end update

            }//end else

        }//end uri

    }//end onImageUriChanged


    //function for update password
    fun onPasswordChanged(newValue: String) {

        //update password state here
        _state.update {
            it.copy(
                passwordValue = newValue
            )
        }//end update

    }//end onPasswordChanged


    //function for update password confirmation
    fun onPasswordConfirmationChanged(newValue: String) {

        //update password confirmation here
        _state.update {
            it.copy(
                passwordConfirmPassword = newValue
            )
        }//end update

    }//end onPasswordConfirmationChanged


    //function for update last name
    fun onLastNameChanged(newValue: String) {

        //update last name here
        _state.update {
            it.copy(
                lastNameValue = newValue
            )
        }//end update

    }//end onLastNameChanged


    //function for update first name
    fun onFirstNameChanged(newValue: String) {

        //update first name here
        _state.update {
            it.copy(
                firstNameValue = newValue
            )
        }//end update

    }//end onFirstNameChanged


    //function for update profile info
    fun onUpdateProfileInfo() {

        //create coroutine builder for call suspend function in it
        viewModelScope.launch(Dispatchers.IO) {

            if (
                state.value.lastNameValue.trim().length in 2..30 ||
                state.value.firstNameValue.trim().length in 2..20 ||
                (state.value.passwordValue.trim() == state.value.passwordConfirmPassword.trim() &&
                        state.value.passwordValue.trim().length in 6..30) ||
                state.value.imageUploaded
            ) {

                try {

                    updateProfileInfoUseCase(
                        firstName = state.value.firstNameValue.trim().ifEmpty { null },
                        lastName = state.value.lastNameValue.trim().ifEmpty { null },
                        password = state.value.passwordValue.trim().ifEmpty { null },
                        imageUri = if (state.value.imageUploaded) state.value.imageUri else null
                    ).collectLatest { status ->

                        when (status) {

                            is Status.Success -> {

                                when (status.toData()?.statusCode) {

                                    200 -> {
                                        _state.update {
                                            it.copy(
                                                updateProfileEventStatus = state.value
                                                    .updateProfileEventStatus.copy(
                                                        success = true,
                                                        loading = false
                                                    ),
                                                imageUploaded = false
                                            )
                                        }//end update

                                        delay(1000)

                                        _state.update {
                                            it.copy(
                                                updateProfileEventStatus = state.value
                                                    .updateProfileEventStatus.copy(
                                                        success = false,
                                                        loading = false
                                                    )
                                            )
                                        }//end update

                                    }//end success case

                                    401 -> {

                                        logoutFromLocalDatabaseUseCase()

                                        _state.update {
                                            it.copy(
                                                updateProfileEventStatus = state.value
                                                    .updateProfileEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        unAuthorized = true
                                                    )
                                            )
                                        }//end update

                                    }//end unAuthorized case

                                    404, 500 -> {
                                        _state.update {
                                            it.copy(
                                                updateProfileEventStatus = state.value
                                                    .updateProfileEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.updateProfileEventStatus.serverError
                                                    )
                                            )
                                        }//end update
                                    }//end error server case

                                }//end when

                            }//end success case

                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        updateProfileEventStatus = state.value
                                            .updateProfileEventStatus.copy(
                                                success = false,
                                                loading = true
                                            )
                                    )
                                }//end update

                            }//end loading case

                            is Status.Error -> {

                                when (status.status) {

                                    400 -> {

                                        _state.update {
                                            it.copy(
                                                updateProfileEventStatus = state.value
                                                    .updateProfileEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        internetError = !state.value.updateProfileEventStatus.internetError
                                                    )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                updateProfileEventStatus = state.value
                                                    .updateProfileEventStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.updateProfileEventStatus.serverError
                                                    )
                                            )
                                        }//end update

                                    }//end server error case

                                }//end when

                            }//end error case

                        }//end when

                    }//end collectLatest

                }//end try
                catch (ex: IOException) {

                    //failed connected with internet
                    _state.update {
                        it.copy(
                            updateProfileEventStatus = state.value.updateProfileEventStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.updateProfileEventStatus.internetError
                            )
                        )
                    }//end update

                }//end catch

            }//end if
            else {

                _state.update {
                    it.copy(
                        updateProfileEventStatus = state.value.updateProfileEventStatus.copy(
                            dataError = !state.value.updateProfileEventStatus.dataError
                        )
                    )
                }//end update

            }//end else

        }//end coroutine builder

    }//end onUpdateProfileInfo

}//end ProfileViewModel