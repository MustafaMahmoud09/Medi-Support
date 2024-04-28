package com.example.heart.rate.domain.usecase.declarations

import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IGetPageHistoryRecordsUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): UnEffectResponse<List<SimpleHeartRateModel>>

}//end IGetPageHistoryRecordsUseCase