package com.example.blood.pressure.domain.usecase.declarations

import com.example.blood.pressure.domain.model.AdviceBloodPressureModel
import kotlinx.coroutines.flow.Flow

interface IGetLatestBloodPressureMeasurementUserCase {

    suspend operator fun invoke(): Flow<List<AdviceBloodPressureModel>>

}//end IGetLatestBloodPressureMeasurementUserCae