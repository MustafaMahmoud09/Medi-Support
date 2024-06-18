package com.example.onlinebooking.presentation.uiState.state

import androidx.paging.PagingData
import com.example.online.booking.domain.model.OnlineBookingModel
import com.example.online.booking.domain.model.PaymentModel
import kotlinx.coroutines.flow.Flow

data class OnlineDetailsUiState(
    val totalOnlineBookingStatus: Flow<PagingData<OnlineBookingModel>>? = null,
    val cacheTotalOnlineBookingStatus: Flow<PagingData<OnlineBookingModel>>? = null,
    val refreshState: Boolean = false,
    val bookingPlaceHolder: OnlineBookingModel = OnlineBookingModel(
        id = 0,
        doctorName = "js",
        specialization = "js",
        activeStatus = true,
        bookingStatus = 0
    ),
    val startRunning: Boolean = true,
    val getPaymentIntentSecretStatus: GetPaymentIntentSecretStatus = GetPaymentIntentSecretStatus()
)

data class GetPaymentIntentSecretStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val paymentDone: Boolean = false,
    val bookingId: Long = 0,
    val paymentModel: PaymentModel = PaymentModel(
        paymentIntent = "",
        bookingId = 0
    ),
)
