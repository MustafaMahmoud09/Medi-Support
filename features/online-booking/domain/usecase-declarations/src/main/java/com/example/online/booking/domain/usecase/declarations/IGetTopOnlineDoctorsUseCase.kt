package com.example.offline.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.model.OfflineDoctorModel
import kotlinx.coroutines.flow.Flow

interface IGetTopOfflineDoctorsUseCase {

    suspend operator fun invoke(): Flow<Status<EffectResponse<List<OfflineDoctorModel>>>>

}//end GetTopOfflineDoctorsUseCase