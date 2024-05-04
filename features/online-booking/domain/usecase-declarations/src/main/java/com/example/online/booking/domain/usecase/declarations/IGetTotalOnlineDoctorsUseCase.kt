package com.example.online.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.online.booking.domain.model.OnlineDoctorModel

interface IGetTotalOnlineDoctorsUseCase {

    suspend operator fun invoke(
        page: Int
    ): UnEffectResponse<List<OnlineDoctorModel>>

}//end IGetTotalOnlineDoctorsUseCase