package com.example.offline.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.offline.booking.domain.usecase.declarations.IRateOfflineDoctorUseCase
import kotlinx.coroutines.flow.Flow

class RateOfflineDoctorUseCase(
    private val offlineBookingRepository: IOfflineBookingRepository,
): IRateOfflineDoctorUseCase {

    //function for make request on repository for rate offline doctor
    override suspend fun invoke(
        doctorId: Long,
        rate: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return offlineBookingRepository.rateDoctor(
            doctorId = doctorId,
            rate = rate
        )

    }//end invoke

}//end RateOfflineDoctorUseCase