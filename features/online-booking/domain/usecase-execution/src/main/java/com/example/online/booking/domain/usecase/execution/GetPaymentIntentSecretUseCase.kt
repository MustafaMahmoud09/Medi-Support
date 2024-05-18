@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.online.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.dto.declarations.payment.IPaymentDto
import com.example.online.booking.domain.mapper.declarations.child.IPaymentDtoToPaymentModelMapper
import com.example.online.booking.domain.model.PaymentModel
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.online.booking.domain.usecase.declarations.IGetPaymentIntentSecretUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetPaymentIntentSecretUseCase(
    private val onlineBookingRepository: IOnlineBookingRepository,
    private val paymentDtoToPaymentModelMapper: IPaymentDtoToPaymentModelMapper
) : IGetPaymentIntentSecretUseCase {

    //function for make on repository for get payment intent secret
    override suspend fun invoke(
        bookingId: Long
    ): Flow<Status<EffectResponse<List<PaymentModel>>>> {

        return channelFlow<Status<EffectResponse<List<PaymentModel>>>> {

            onlineBookingRepository.getPaymentIntent(
                bookingId = bookingId
            ).collect { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get payment dto here
                            val paymentDto = status.toData()?.body?.response

                            //map payment dto to payment model
                            val paymentModel = paymentDtoToPaymentModelMapper.listConvertor(
                                list = listOf(paymentDto as IPaymentDto)
                            )

                            //send data to flow
                            trySend(
                                Status.Success(
                                    data = EffectResponse(
                                        body = paymentModel,
                                        statusCode = status.toData()?.statusCode!!
                                    )
                                )
                            )

                        }//end if
                        else {

                            //send data to flow
                            trySend(
                                Status.Success(
                                    data = EffectResponse(
                                        body = emptyList(),
                                        statusCode = status.toData()?.statusCode!!
                                    )
                                )
                            )

                        }//end else

                    }//end success case

                    is Status.Error -> {
                        trySend(status)
                    }//end error case

                    is Status.Loading -> {
                        trySend(status)
                    }//end loading case

                }//end when

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetPaymentIntentSecretUseCase