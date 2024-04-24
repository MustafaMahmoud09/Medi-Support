package com.example.blood.pressure.domain.usecase.declarations

import com.example.blood.pressure.domain.model.ChartBloodPressureModel
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.Flow

interface IGetLastWeekSystolicRecordsUseCase {

    suspend operator fun invoke()
            : Flow<Status<EffectResponse<List<ChartBloodPressureModel>>>>

}//end IGetLastWeekSystolicRecordsUseCase