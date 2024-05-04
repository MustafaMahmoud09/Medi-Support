package com.example.online.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.model.OnlineDoctorModel
import kotlinx.coroutines.flow.Flow

interface IGetTopOnlineDoctorsUseCase {

    suspend operator fun invoke(): Flow<Status<EffectResponse<List<OnlineDoctorModel>>>>

}//end IGetTopOnlineDoctorsUseCase