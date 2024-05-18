package com.example.online.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.model.PaymentModel
import kotlinx.coroutines.flow.Flow

interface IGetPaymentIntentSecretUseCase {

    suspend operator fun invoke(
        bookingId: Long
    ): Flow<Status<EffectResponse<List<PaymentModel>>>>

}//end IGetPaymentIntentSecretUseCase