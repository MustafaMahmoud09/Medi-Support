package com.example.blood.pressure.domain.usecase.execution

import com.example.blood.pressure.domain.mapper.declarations.child.IBloodPressureEntityToSimpleBloodPressureModelMapper
import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
import com.example.blood.pressure.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetLastHistoryRecordsUseCase(
    private val bloodPressureRepository: IBloodPressureRepository,
    private val bloodPressureEntityToSimpleBloodPressureModelMapper: IBloodPressureEntityToSimpleBloodPressureModelMapper
) : IGetLastHistoryRecordsUseCase {

    //function for provide last blood pressure history records
    override suspend fun invoke(): Flow<List<SimpleBloodPressureModel>> {

        //return flow with emit list of SimpleBloodPressureModel
        return flow {

            //observe get last history flow
            //and collect data
            //and convert each entity to model
            //and emit  remodel to flow
            bloodPressureRepository
                .getLatestHistoryBloodPressureRecords().collect { bloodPressureEntities ->

                    //map data from entities to simple model
                    val bloodPressureModels =
                        bloodPressureEntityToSimpleBloodPressureModelMapper.listConvertor(
                            list = bloodPressureEntities
                        )

                    //emit blood pressure models here
                    emit(bloodPressureModels)

                }//end collect

        }//end flow

    }//end invoke

}//end GetLastHistoryRecordsUseCase