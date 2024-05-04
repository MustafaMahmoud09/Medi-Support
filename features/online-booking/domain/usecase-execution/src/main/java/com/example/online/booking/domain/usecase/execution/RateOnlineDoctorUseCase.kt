package com.example.online.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.online.booking.domain.usecase.declarations.IRateOnlineDoctorUseCase
import kotlinx.coroutines.flow.Flow

class RateOnlineDoctorUseCase(
    private val onlineBookingRepository: IOnlineBookingRepository,
): IRateOnlineDoctorUseCase {

    //function for make request on repository for rate offline doctor
    override suspend fun invoke(
        doctorId: Long,
        rate: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return onlineBookingRepository.rateDoctor(
            doctorId = doctorId,
            rate = rate
        )

    }//end invoke

}//end RateOfflineDoctorUseCase