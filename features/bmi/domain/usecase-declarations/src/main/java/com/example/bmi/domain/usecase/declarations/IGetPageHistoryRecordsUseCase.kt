package com.example.bmi.domain.usecase.declarations

import com.example.bmi.domain.model.SimpleBMIModel
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IGetPageHistoryRecordsUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): UnEffectResponse<List<SimpleBMIModel>>

}//end IGetPageHistoryRecordsUseCase