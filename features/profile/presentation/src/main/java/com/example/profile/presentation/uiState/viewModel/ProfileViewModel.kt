package com.example.profile.presentation.uiState.viewModel

import android.net.Uri
import com.example.profile.presentation.uiState.state.ProfileUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(ProfileUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //function for update image uri
    fun onImageUriChanged(uri: Uri?) {

        //check uri not equal null
        if (uri != null) {

            //update image uri state here
            _state.update {
                it.copy(
                    imageUri = uri
                )
            }//end update

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

}//end ProfileViewModel