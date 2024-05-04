package com.example.online.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.online.booking.domain.usecase.declarations.IBookOnlineAppointmentUseCase
import kotlinx.coroutines.flow.Flow

class BookOnlineAppointmentUseCase(
    private val onlineBookingRepository: IOnlineBookingRepository,
) : IBookOnlineAppointmentUseCase {

    //function for make request on repository for book offline appointment
    override suspend fun invoke(
        doctorId: Long
    ): Flow<Status<EffectResponse<Any>>> {

        return onlineBookingRepository.bookOnlineAppointment(
            doctorId = doctorId
        )

    }//end invoke

}//end BookOfflineAppointmentUseCase