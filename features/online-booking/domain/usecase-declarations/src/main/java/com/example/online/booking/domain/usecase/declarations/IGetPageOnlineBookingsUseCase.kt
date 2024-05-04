package com.example.blood.sugar.domain.usecase.declarations

import com.example.blood.sugar.domain.model.SimpleBloodSugarModel
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IGetPageHistoryRecordsUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): UnEffectResponse<List<SimpleBloodSugarModel>>

}//end IGetAllHistoryRecordsUseCase