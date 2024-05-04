package com.example.offline.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.offline.booking.domain.model.OfflineBookingModel

interface IGetPageOfflineBookingsUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): UnEffectResponse<List<OfflineBookingModel>>

}//end IGetPageOfflineBookingsUseCase