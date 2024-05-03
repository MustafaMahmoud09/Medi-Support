package com.example.offline.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.offline.booking.domain.model.OfflineDoctorModel

interface ISearchOnOfflineDoctorsUseCase {

    suspend operator fun invoke(
        page: Int,
        searchKey: String
    ): UnEffectResponse<List<OfflineDoctorModel>>

}//end ISearchOnOfflineDoctorsUseCase