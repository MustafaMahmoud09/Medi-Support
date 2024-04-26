package com.example.blood.pressure.domain.usecase.declarations

import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import com.example.libraries.core.remote.data.response.status.UnEffectResponse

interface IGetPageHistoryRecordUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<SimpleBloodPressureModel>>

}//end IGetAllHistoryRecordUseCase