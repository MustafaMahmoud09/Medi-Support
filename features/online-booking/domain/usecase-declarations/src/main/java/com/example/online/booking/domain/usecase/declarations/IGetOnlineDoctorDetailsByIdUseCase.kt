package com.example.online.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.model.OnlineDoctorDetailsModel
import kotlinx.coroutines.flow.Flow

interface IGetOnlineDoctorDetailsByIdUseCase {

    suspend operator fun invoke(
        doctorId: Long
    ): Flow<Status<EffectResponse<OnlineDoctorDetailsModel>>>

}//end GetOfflineDoctorDetailsByIdUseCase