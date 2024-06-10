package com.example.onlinebooking.presentation.uiState.viewModel

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.usecase.declarations.IGetPageOnlineBookingsUseCase
import com.example.online.booking.domain.usecase.declarations.IGetPaymentIntentSecretUseCase
import com.example.online.booking.pagination.OnlineBookingDataSource
import com.example.onlinebooking.presentation.uiState.state.OnlineDetailsUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnlineDetailsViewModel @Inject constructor(
    private val getPageOnlineBookingsUseCase: IGetPageOnlineBookingsUseCase,
    private val applicationContext: Context,
    private val getPaymentIntentSecretUseCase: IGetPaymentIntentSecretUseCase,
    private val paymentPublishedKey: String
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(OnlineDetailsUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onInitPaymentSetting()
        onGetTotalOnlineBookings()
        viewModelScope.launch {
            delay(250)
            _state.update {
                it.copy(
                    startRunning = false
                )
            }//end update
        }//end coroutine builder scope

    }//end init


    //function for initialize payment setting
    private fun onInitPaymentSetting() {

        PaymentConfiguration.init(
            applicationContext,
            paymentPublishedKey
        )

    }//end onInitPaymentSetting


    //function for handle payment result
    fun handlePaymentResult(result: PaymentSheetResult) {
        when (result) {
            PaymentSheetResult.Canceled -> {

                _state.update {
                    it.copy(
                        getPaymentIntentSecretStatus = state.value.getPaymentIntentSecretStatus.copy(
                            success = false
                        )
                    )
                }//end update

            }//end cancel status

            PaymentSheetResult.Completed -> {

                _state.update {
                    it.copy(
                        getPaymentIntentSecretStatus = state.value.getPaymentIntentSecretStatus.copy(
                            paymentDone = true,
                        ),
                    )
                }//end update

                onUpdateOnlineBookingPaymentStatus(
                    newStatus = 2
                )

            }//end Completed status

            is PaymentSheetResult.Failed -> {

                _state.update {
                    it.copy(
                        getPaymentIntentSecretStatus = state.value.getPaymentIntentSecretStatus.copy(
                            success = false
                        )
                    )
                }//end update

            }//end failed status

        }//end when conditions

    }//end handlePaymentResult


    //function for update online booking selected to 2 status
    private fun onUpdateOnlineBookingPaymentStatus(newStatus: Int) {

        _state.update {
            it.copy(
                cacheTotalOnlineBookingStatus = state.value.totalOnlineBookingStatus
            )
        }//end update

        val newOnlineBooking = state.value.totalOnlineBookingStatus?.map { onlineBookings ->

            //map reminders here
            onlineBookings.map { booking ->

                if (booking.id == state.value.getPaymentIntentSecretStatus.bookingId) {
                    booking.copy(
                        bookingStatus = newStatus
                    )
                }//end if

                else {
                    booking
                }//end else

            }//end child map

        }//end parent map

        _state.update {
            it.copy(
                totalOnlineBookingStatus = newOnlineBooking
            )
        }//end update

    }//end onUpdateOnlineBookingStatus


    //function for get total offline doctors
    private fun onGetTotalOnlineBookings() {

        //get total offline doctors flow here
        val totalOnlineBookingFlow = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            OnlineBookingDataSource(
                getPageOnlineBookingsUseCase = getPageOnlineBookingsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change total offline doctor state here
        _state.update {
            it.copy(
                totalOnlineBookingStatus = totalOnlineBookingFlow
            )
        }//end update

    }//end onGetTotalOnlineDoctors


    //function for get payment intent secret
    fun onGetPaymentIntentSecret(
        bookingId: Long
    ) {

        //create coroutine builder scope
        viewModelScope.launch(Dispatchers.IO) {

            try {

                getPaymentIntentSecretUseCase(
                    bookingId = bookingId
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            when (status.toData()?.statusCode) {

                                200 -> {
                                    _state.update {
                                        it.copy(
                                            getPaymentIntentSecretStatus = state.value
                                                .getPaymentIntentSecretStatus.copy(
                                                    success = true,
                                                    loading = false,
                                                    paymentModel = status.toData()?.body!![0]
                                                )
                                        )
                                    }//end update
                                }//end success case

                                404, 500 -> {
                                    _state.update {
                                        it.copy(
                                            getPaymentIntentSecretStatus = state.value
                                                .getPaymentIntentSecretStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.getPaymentIntentSecretStatus.serverError
                                                )
                                        )
                                    }//end update
                                }//end error server case

                                422 -> {

                                    _state.update {
                                        it.copy(
                                            getPaymentIntentSecretStatus = state.value.getPaymentIntentSecretStatus.copy(
                                                paymentDone = true,
                                                success = false,
                                                loading = false,
                                            ),
                                        )
                                    }//end update

                                    onUpdateOnlineBookingPaymentStatus(
                                        newStatus = 2
                                    )

                                }//end appointment not valid case

                                403 -> {

                                    _state.update {
                                        it.copy(
                                            getPaymentIntentSecretStatus = state.value.getPaymentIntentSecretStatus.copy(
                                                success = false,
                                                loading = false,
                                            ),
                                        )
                                    }//end update

                                    onDeleteOnlineBookingFromPagination(bookingId)

                                }//end appointment not valid case

                            }//end when

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    getPaymentIntentSecretStatus = state.value
                                        .getPaymentIntentSecretStatus.copy(
                                            success = false,
                                            loading = true,
                                            bookingId = bookingId,
                                            paymentDone = false
                                        )
                                )
                            }//end update

                        }//end loading case

                        is Status.Error -> {

                            when (status.status) {

                                400 -> {

                                    _state.update {
                                        it.copy(
                                            getPaymentIntentSecretStatus = state.value
                                                .getPaymentIntentSecretStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.getPaymentIntentSecretStatus.internetError
                                                )
                                        )
                                    }//end update

                                }//end internet error case

                                500 -> {

                                    _state.update {
                                        it.copy(
                                            getPaymentIntentSecretStatus = state.value
                                                .getPaymentIntentSecretStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.getPaymentIntentSecretStatus.serverError
                                                )
                                        )
                                    }//end update

                                }//end server error case

                            }//end when

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: Exception) {

                _state.update {
                    it.copy(
                        getPaymentIntentSecretStatus = state.value
                            .getPaymentIntentSecretStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.getPaymentIntentSecretStatus.internetError
                            )
                    )
                }//end update

            }//end catch

        }//end coroutine builder

    }//end onGetPaymentIntentSecret


    //function for delete online booking from pagination
    private fun onDeleteOnlineBookingFromPagination(
        bookingId: Long
    ) {

        //update total online booking
        _state.update {
            it.copy(
                cacheTotalOnlineBookingStatus = state.value.totalOnlineBookingStatus
            )
        }//end update

        val bookings = state.value.totalOnlineBookingStatus?.map { onlineBookings ->
            onlineBookings.filter { booking ->
                booking.id != bookingId
            }//end filter
        }//end map

        _state.update {
            it.copy(
                totalOnlineBookingStatus = bookings
            )
        }

    }//end onDeleteOnlineBookingFromPagination


}//end OnlineDetailsViewModel