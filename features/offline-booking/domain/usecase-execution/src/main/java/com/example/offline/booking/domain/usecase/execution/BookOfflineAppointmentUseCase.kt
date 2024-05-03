package com.example.offline.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.offline.booking.domain.usecase.declarations.IBookOfflineAppointmentUseCase
import kotlinx.coroutines.flow.Flow

class BookOfflineAppointmentUseCase(
    private val offlineBookingRepository: IOfflineBookingRepository,
) : IBookOfflineAppointmentUseCase {

    //function for make request on repository for book offline appointment
    override suspend fun invoke(
        dateId: Long,
        timeId: Long,
        doctorId: Long
    ): Flow<Status<EffectResponse<Any>>> {

        return offlineBookingRepository.bookOfflineAppointment(
            dateId = dateId,
            timeId = timeId,
            doctorId = doctorId
        )

    }//end invoke

}//end BookOfflineAppointmentUseCase