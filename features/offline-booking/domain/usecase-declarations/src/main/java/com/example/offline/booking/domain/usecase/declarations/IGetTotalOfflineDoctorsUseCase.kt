package com.example.offline.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.offline.booking.domain.model.OfflineDoctorModel

interface IGetTotalOfflineDoctorsUseCase {

    suspend operator fun invoke(
        page: Int
    ): UnEffectResponse<List<OfflineDoctorModel>>

}//end IGetTotalOfflineDoctorsUseCase