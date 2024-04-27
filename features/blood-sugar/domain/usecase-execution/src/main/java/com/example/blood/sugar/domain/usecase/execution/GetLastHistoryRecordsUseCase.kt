package com.example.blood.sugar.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToSimpleBloodSugarModelMapper
import com.example.blood.sugar.domain.model.SimpleBloodSugarModel
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.IGetLastHistoryRecordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetLastHistoryRecordsUseCase(
    private val bloodSugarRepository: IBloodSugarRepository,
    private val bloodSugarEntityToSimpleBloodSugarModelMapper: IBloodSugarEntityToSimpleBloodSugarModelMapper
) : IGetLastHistoryRecordsUseCase {

    //function for make request on repository for provide latest history blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<SimpleBloodSugarModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            bloodSugarRepository.getLatestMeasurements(
                numberOfMeasurement = 3
            ).collect { bloodSugarEntity ->

                //map data from entity to chart model here
                val bloodSugarModels = bloodSugarEntityToSimpleBloodSugarModelMapper.listConvertor(
                    list = bloodSugarEntity
                )

                //emit data after mapping here
                trySend(bloodSugarModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLastHistoryRecordsUseCase