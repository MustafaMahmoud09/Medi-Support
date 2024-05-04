package com.example.onlinebooking.presentation.uiState.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.usecase.declarations.IBookOnlineAppointmentUseCase
import com.example.online.booking.domain.usecase.declarations.IGetOnlineDoctorDetailsByIdUseCase
import com.example.online.booking.domain.usecase.declarations.IRateOnlineDoctorUseCase
import com.example.onlinebooking.presentation.uiElement.screens.booking.OnlineBookingArgs
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.example.onlinebooking.presentation.uiState.state.OnlineBookingUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.IOException

@HiltViewModel
internal class OnlineBookingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getOnlineDoctorDetailsByIdUseCase: IGetOnlineDoctorDetailsByIdUseCase,
    private val bookOnlineAppointmentUseCase: IBookOnlineAppointmentUseCase,
    private val rateOnlineDoctorUseCase: IRateOnlineDoctorUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(OnlineBookingUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking arguments here
    private val arguments: OnlineBookingArgs =
        OnlineBookingArgs(
            savedStateHandle
        )


    init {

        _state.update {
            it.copy(
                doctorId = arguments.doctorId.toLong()
            )
        }//end update

        viewModelScope.launch {
            delay(250)
            _state.update {
                it.copy(
                    startRunning = false
                )
            }//end update
        }//end coroutine builder scope

        //on get doctor details
        onGetDoctorDetails()

    }//end init


    //function for get doctor details
    private fun onGetDoctorDetails() {

        //create coroutine builder for call suspend functions in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                getOnlineDoctorDetailsByIdUseCase(
                    doctorId = state.value.doctorId
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            if (status.toData()?.statusCode == 200) {

                                //update top offline doctors status to success
                                _state.update {
                                    it.copy(
                                        onlineDoctorDetailsStatus = state.value
                                            .onlineDoctorDetailsStatus.copy(
                                                loading = false,
                                                data = status.data.body
                                            )
                                    )
                                }//end update

                            }//end if

                            else if (status.toData()?.statusCode == 404) {

                                //update top offline doctors status to success
                                _state.update {
                                    it.copy(
                                        onlineDoctorDetailsStatus = state.value
                                            .onlineDoctorDetailsStatus.copy(
                                                doctorDeleted = true
                                            )
                                    )
                                }//end update

                            }//end else

                        }//end success case

                        is Status.Error -> {

                        }//end error case

                        is Status.Loading -> {

                            //update top offline doctors status to loading
                            _state.update {
                                it.copy(
                                    onlineDoctorDetailsStatus = state.value
                                        .onlineDoctorDetailsStatus.copy(
                                            loading = true
                                        )
                                )
                            }//end update

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: Exception) {
                ex.message?.let { Log.d("TAG", it) }
            }//end catch

        }//end coroutine builder scope

    }//end onGetDoctorDetails


    //function for book online appointment
    fun onBookOnlineAppointment() {

        //create coroutine builder scope here
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make add blood pressure record use case here
                //observe use case flow
                //collect add blood pressure record status
                bookOnlineAppointmentUseCase(
                    doctorId = state.value.doctorId,
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            when (status.toData()?.statusCode) {

                                201 -> {
                                    _state.update {
                                        it.copy(
                                            bookOnlineAppointmentStatus = state.value
                                                .bookOnlineAppointmentStatus.copy(
                                                    success = true,
                                                    loading = false
                                                )
                                        )
                                    }//end update
                                }//end success case

                                404, 500 -> {
                                    _state.update {
                                        it.copy(
                                            bookOnlineAppointmentStatus = state.value
                                                .bookOnlineAppointmentStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.bookOnlineAppointmentStatus.serverError
                                                )
                                        )
                                    }//end update
                                }//end error server case

                                422 -> {

                                    _state.update {
                                        it.copy(
                                            bookOnlineAppointmentStatus = state.value
                                                .bookOnlineAppointmentStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    doctorNotOnline = !state.value.bookOnlineAppointmentStatus.doctorNotOnline
                                                )
                                        )
                                    }//end update

                                }//end appointment not valid case

                            }//end when

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    bookOnlineAppointmentStatus = state.value
                                        .bookOnlineAppointmentStatus.copy(
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
                                            bookOnlineAppointmentStatus = state.value
                                                .bookOnlineAppointmentStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.bookOnlineAppointmentStatus.internetError
                                                )
                                        )
                                    }//end update

                                }//end internet error case

                                500 -> {

                                    _state.update {
                                        it.copy(
                                            bookOnlineAppointmentStatus = state.value
                                                .bookOnlineAppointmentStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.bookOnlineAppointmentStatus.serverError
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

                _state.update {
                    it.copy(
                        bookOnlineAppointmentStatus = state.value
                            .bookOnlineAppointmentStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.bookOnlineAppointmentStatus.internetError
                            )
                    )
                }//end update

            }//end catch

        }//end coroutine builder scope

    }//end onBookOfflineAppointment


    //function for rate doctor
    fun onRateOfflineDoctor(rate: Int) {

//        if (rate.toFloat() != state.value.offlineDoctorDetailsStatus.data?.userRating) {
        if (state.value.onlineDoctorDetailsStatus.data?.userRating == 0f) {

            //update user rate here first
            _state.update {
                it.copy(
                    onlineDoctorDetailsStatus = state.value.onlineDoctorDetailsStatus.copy(
                        data = state.value.onlineDoctorDetailsStatus.data?.copy(
                            userRating = rate.toFloat()
                        )
                    )
                )
            }//end update

            //create coroutine builder here
            viewModelScope.launch(Dispatchers.IO){

                //make rating to offline doctor here
                rateOnlineDoctorUseCase(
                    rate = rate,
                    doctorId = state.value.doctorId
                ).collectLatest { status -> }

            }//end launch

        }//end if

    }//end onRateOfflineDoctor

}//end OfflineBookingViewModel