package com.example.online.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.model.OfflineDoctorDetailsModel
import kotlinx.coroutines.flow.Flow

interface IGetOfflineDoctorDetailsByIdUseCase {

    suspend operator fun invoke(
        doctorId: Long
    ): Flow<Status<EffectResponse<OfflineDoctorDetailsModel>>>

}//end GetOfflineDoctorDetailsByIdUseCase