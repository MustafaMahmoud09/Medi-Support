package com.example.heartrate.domain.usecase

import com.example.blood.sugar.domain.mapper.declarations.child.IBloodSugarEntityToChartBloodSugarModelMapper
import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateEntityToChartHeartRateModelMapper
import com.example.blood.sugar.domain.model.ChartBloodSugarModel
import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.IGetLastWeekBloodSugarRecordsUseCase
import com.example.heart.rate.domain.domain.model.ChartHeartRateModel
import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.IGetLastWeekHeartRateRecordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import java.util.LinkedList

class GetLastWeekBloodSugarRecordsUseCase(
    private val heartRateRepository: IHeartRateRepository,
    private val heartRateEntityToChartHeartRateModelMapper: IHeartRateEntityToChartHeartRateModelMapper
) : IGetLastWeekHeartRateRecordsUseCase {


    //function for provide last week blood sugar records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<ChartHeartRateModel>> {

        //return flow contain on chart blood sugar model
        return channelFlow {

            //make request on repository for get last blood sugar records
            heartRateRepository.getLastWeekMeasurement().collect { bloodSugarEntity ->

                //map data from entity to chart model here
                val bloodSugarModels = heartRateEntityToChartHeartRateModelMapper.listConvertor(
                    list = bloodSugarEntity.reversed()
                )

                //emit data after mapping here
                trySend(bloodSugarModels)

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetLastWeekBloodSugarRecordsUseCase