package com.example.online.booking.domain.usecase.declarations

import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.online.booking.domain.model.OnlineBookingModel

interface IGetPageOnlineBookingsUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): UnEffectResponse<List<OnlineBookingModel>>

}//end IGetAllHistoryRecordsUseCase