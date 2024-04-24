package com.example.blood.pressure.domain.usecase.declarations

import com.example.blood.pressure.domain.model.ChartBloodPressureModel
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IGetLastWeekDiastolicRecordsUseCase {

    suspend operator fun invoke()
            : Flow<Status<EffectResponse<List<ChartBloodPressureModel>>>>

}//end IGetLastWeekDiastolicRecordsUseCase