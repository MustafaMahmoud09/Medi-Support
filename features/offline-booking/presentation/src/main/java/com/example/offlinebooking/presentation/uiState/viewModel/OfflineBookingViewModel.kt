package com.example.offlinebooking.presentation.uiState.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.model.TimeModel
import com.example.offline.booking.domain.usecase.declarations.IBookOfflineAppointmentUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetBookingTimeUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetOfflineDoctorDetailsByIdUseCase
import com.example.offline.booking.domain.usecase.declarations.IRateOfflineDoctorUseCase
import com.example.offlinebooking.presentation.uiElement.screens.booking.OfflineBookingArgs
import com.example.offlinebooking.presentation.uiState.state.InfiniteGetterStatus
import com.example.offlinebooking.presentation.uiState.state.OfflineBookingUiState
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
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class OfflineBookingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getOfflineDoctorDetailsByIdUseCase: IGetOfflineDoctorDetailsByIdUseCase,
    private val getBookingTimeUseCase: IGetBookingTimeUseCase,
    private val bookOfflineAppointmentUseCase: IBookOfflineAppointmentUseCase,
    private val rateOfflineDoctorUseCase: IRateOfflineDoctorUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(OfflineBookingUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking arguments here
    private val bookingArguments: OfflineBookingArgs =
        OfflineBookingArgs(
            savedStateHandle
        )

    init {

        _state.update {
            it.copy(
                doctorId = bookingArguments.doctorId.toLong()
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

        //on get booking date times
        onGetBookingDateTimes()

    }//end init


    //function for book offline appointment
    fun onBookOfflineAppointment() {

        //create coroutine builder scope here
        viewModelScope.launch(Dispatchers.IO) {

            if (
                state.value.dateIdSelected.value != -1L &&
                state.value.timeIdSelected != -1L
            ) {

                try {

                    //make add blood pressure record use case here
                    //observe use case flow
                    //collect add blood pressure record status
                    bookOfflineAppointmentUseCase(
                        dateId = state.value.dateIdSelected.value,
                        doctorId = state.value.doctorId,
                        timeId = state.value.timeIdSelected
                    ).collectLatest { status ->

                        when (status) {

                            is Status.Success -> {

                                when (status.toData()?.statusCode) {

                                    200 -> {
                                        _state.update {
                                            it.copy(
                                                bookOfflineAppointmentStatus = state.value
                                                    .bookOfflineAppointmentStatus.copy(
                                                        success = true,
                                                        loading = false
                                                    )
                                            )
                                        }//end update
                                    }//end success case

                                    404, 500 -> {
                                        _state.update {
                                            it.copy(
                                                bookOfflineAppointmentStatus = state.value
                                                    .bookOfflineAppointmentStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.bookOfflineAppointmentStatus.serverError
                                                    )
                                            )
                                        }//end update
                                    }//end error server case

                                    405 -> {

                                        _state.update {
                                            it.copy(
                                                bookOfflineAppointmentStatus = state.value
                                                    .bookOfflineAppointmentStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        appointmentNotValid = !state.value.bookOfflineAppointmentStatus.appointmentNotValid
                                                    )
                                            )
                                        }//end update

                                        onTimeIdSelectedDestroy()

                                    }//end appointment not valid case

                                    420 -> {

                                        _state.update {
                                            it.copy(
                                                bookOfflineAppointmentStatus = state.value
                                                    .bookOfflineAppointmentStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        alreadyHaveBooking = !state.value.bookOfflineAppointmentStatus.alreadyHaveBooking
                                                    )
                                            )
                                        }//end update

                                    }//end appointment not valid case

                                }//end when

                            }//end success case

                            is Status.Loading -> {

                                _state.update {
                                    it.copy(
                                        bookOfflineAppointmentStatus = state.value
                                            .bookOfflineAppointmentStatus.copy(
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
                                                bookOfflineAppointmentStatus = state.value
                                                    .bookOfflineAppointmentStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        internetError = !state.value.bookOfflineAppointmentStatus.internetError
                                                    )
                                            )
                                        }//end update

                                    }//end internet error case

                                    500 -> {

                                        _state.update {
                                            it.copy(
                                                bookOfflineAppointmentStatus = state.value
                                                    .bookOfflineAppointmentStatus.copy(
                                                        success = false,
                                                        loading = false,
                                                        serverError = !state.value.bookOfflineAppointmentStatus.serverError
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
                            bookOfflineAppointmentStatus = state.value
                                .bookOfflineAppointmentStatus.copy(
                                    success = false,
                                    loading = false,
                                    internetError = !state.value.bookOfflineAppointmentStatus.internetError
                                )
                        )
                    }//end update

                }//end catch

            }//end if
            else {

                _state.update {
                    it.copy(
                        bookOfflineAppointmentStatus = state.value
                            .bookOfflineAppointmentStatus.copy(
                                success = false,
                                loading = false,
                                appointNotSelected = !state.value.bookOfflineAppointmentStatus.appointNotSelected
                            )
                    )

                }//end update

            }//end else

        }//end coroutine builder scope

    }//end onBookOfflineAppointment


    //on time selected destroy
    private fun onTimeIdSelectedDestroy() {

        val dateTimes = state.value.dateTimeStatus.data
        val dateTimeTemp = LinkedList<TimeModel>()

        dateTimes?.forEach { time ->
            if (time.id != state.value.timeIdSelected) {
                dateTimeTemp.add(time)
            }//end if
        }//end forEach

        _state.update {
            it.copy(
                timeIdSelected = -1,
                dateTimeStatus = state.value.dateTimeStatus.copy(
                    data = dateTimeTemp
                )
            )
        }//end update

    }//end onTimeIdSelectedDestroy


    //function for get doctor details
    private fun onGetDoctorDetails() {

        //create coroutine builder for call suspend functions in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                getOfflineDoctorDetailsByIdUseCase(
                    doctorId = state.value.doctorId
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            if (status.toData()?.statusCode == 200) {

                                //update top offline doctors status to success
                                _state.update {
                                    it.copy(
                                        offlineDoctorDetailsStatus = state.value
                                            .offlineDoctorDetailsStatus.copy(
                                                loading = false,
                                                data = status.data.body
                                            ),
                                        timeIdSelected = -1,
                                        dateIdSelected = MutableStateFlow(-1),
                                        dateTimeStatus = InfiniteGetterStatus(
                                            data = emptyList()
                                        ),
                                        numberOfSuccessRequests = 1
                                    )
                                }//end update

                            }//end if

                            else if (status.toData()?.statusCode == 404) {

                                //update top offline doctors status to success
                                _state.update {
                                    it.copy(
                                        offlineDoctorDetailsStatus = state.value
                                            .offlineDoctorDetailsStatus.copy(
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
                                    offlineDoctorDetailsStatus = state.value
                                        .offlineDoctorDetailsStatus.copy(
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


    //function
    fun onRefreshOfflineDoctor() {

        _state.update {
            it.copy(
                refreshState = true
            )
        }//end update

        onGetDoctorDetails()

        _state.update {
            it.copy(
                refreshState = false
            )
        }//end update

    }//end onRefreshOfflineDoctor


    //function for get booking date times
    private fun onGetBookingDateTimes() {

        //create coroutine builder for call suspend functions in it
        viewModelScope.launch(Dispatchers.IO) {

            try {

                state.value.dateIdSelected.collectLatest { dateId ->

                    if (dateId != -1L) {

                        getBookingTimeUseCase(
                            dateId = dateId
                        ).collectLatest { status ->

                            when (status) {

                                is Status.Success -> {

                                    if (status.toData()?.statusCode == 200) {

                                        //update top offline doctors status to success
                                        _state.update {
                                            it.copy(
                                                dateTimeStatus = state.value
                                                    .dateTimeStatus.copy(
                                                        loading = false,
                                                        data = status.data.body
                                                    )
                                            )
                                        }//end update

                                    }//end if

                                }//end success case

                                is Status.Error -> {

                                }//end error case

                                is Status.Loading -> {

                                    //update top offline doctors status to loading
                                    _state.update {
                                        it.copy(
                                            dateTimeStatus = state.value
                                                .dateTimeStatus.copy(
                                                    loading = true
                                                )
                                        )
                                    }//end update

                                }//end error case

                            }//end when

                        }//end collectLatest

                    }//end if
                    else {

                        //update top offline doctors status to empty
                        _state.update {
                            it.copy(
                                dateTimeStatus = state.value
                                    .dateTimeStatus.copy(
                                        loading = false,
                                        data = emptyList()
                                    )
                            )
                        }//end update

                    }//end else

                }//end collectLatest

            }//end try
            catch (ex: Exception) {
                ex.message?.let { Log.d("TAG", it) }
            }//end catch

        }//end coroutine builder scope

    }//end onGetDoctorDetails


    //function for change date id selected
    fun onChangeDateId(newValue: Long) {

        if (newValue != state.value.dateIdSelected.value) {

            //update date id state here
            _state.value.dateIdSelected.update {
                newValue
            }

        }//end if
        else {

            //update date id state here
            _state.value.dateIdSelected.update {
                -1
            }

        }//end else

        //update time id selected state
        _state.update {
            it.copy(
                timeIdSelected = -1
            )
        }//end update

    }//end onDateIdSelectedChanged


    //function for update time id selected
    fun onChangeTimeId(newValue: Long) {

        //update time id state here
        _state.update {
            it.copy(
                timeIdSelected = if (newValue != state.value.timeIdSelected) {
                    newValue
                } else -1
            )
        }//end update

    }//end onChangeTimeId


    //function for rate doctor
    fun onRateOfflineDoctor(rate: Int) {


//        if (rate.toFloat() != state.value.offlineDoctorDetailsStatus.data?.userRating) {
        if (state.value.offlineDoctorDetailsStatus.data?.userRating == 0f) {

            //update user rate here first
            _state.update {
                it.copy(
                    offlineDoctorDetailsStatus = state.value.offlineDoctorDetailsStatus.copy(
                        data = state.value.offlineDoctorDetailsStatus.data?.copy(
                            userRating = rate.toFloat()
                        )
                    )
                )
            }//end update

            //create coroutine builder here
            viewModelScope.launch(Dispatchers.IO) {

                //make rating to offline doctor here
                rateOfflineDoctorUseCase(
                    rate = rate,
                    doctorId = state.value.doctorId
                ).collectLatest { status -> }

            }//end launch

        }//end if

    }//end onRateOfflineDoctor

}//end OfflineBookingViewModel