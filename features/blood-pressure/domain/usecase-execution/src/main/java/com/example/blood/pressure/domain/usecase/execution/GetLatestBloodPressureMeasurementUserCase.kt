package com.example.blood.pressure.domain.usecase.execution

import com.example.blood.pressure.domain.mapper.declarations.child.IBloodPressureEntityToAdviceBloodPressureModelMapper
import com.example.blood.pressure.domain.model.AdviceBloodPressureModel
import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
import com.example.blood.pressure.domain.usecase.declarations.IGetLatestBloodPressureMeasurementUserCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class GetLatestBloodPressureMeasurementUserCase(
    private val bloodPressureRepository: IBloodPressureRepository,
    private val bloodPressureEntityToAdviceBloodPressureModelMapper: IBloodPressureEntityToAdviceBloodPressureModelMapper
) : IGetLatestBloodPressureMeasurementUserCase {

    //function for provide latest blood pressure record with advice
    override suspend fun invoke(): Flow<List<AdviceBloodPressureModel>> {

        //return flow of data contain on latest blood pressure record
        return flow {

            //observe get blood pressure flow
            //collect data and map data to domain model
            bloodPressureRepository
                .getLatestBloodPressureRecord().collect { bloodPressureRecords ->

                    //map data from entity to domain model
                    val adviceBloodPressureModel =
                        bloodPressureEntityToAdviceBloodPressureModelMapper.listConvertor(
                            list = bloodPressureRecords
                        )

                    //emit list contain on advice blood pressure model
                    emit(adviceBloodPressureModel)

                }//end collectLatest

        }//end flow

    }//end invoke

}//end GetLatestBloodPressureMeasurementUserCase