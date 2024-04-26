package com.example.blood.pressure.domain.usecase.execution

import com.example.blood.pressure.domain.mapper.declarations.child.IDescBloodPressureDtoToChartBloodPressureModelMapper
import com.example.blood.pressure.domain.model.ChartBloodPressureModel
import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
import com.example.blood.pressure.domain.usecase.declarations.IGetLastWeekDiastolicRecordsUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetLastWeekDiastolicRecordsUseCase(
    private val bloodPressureRepository: IBloodPressureRepository,
    private val descBloodPressureDtoToChartBloodPressureModelMapper: IDescBloodPressureDtoToChartBloodPressureModelMapper
) : IGetLastWeekDiastolicRecordsUseCase {

    //function for provide last week Diastolic records
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke()
            : Flow<Status<EffectResponse<List<ChartBloodPressureModel>>>> {

        //return stream of data contain on chart blood pressure model
        return channelFlow<Status<EffectResponse<List<ChartBloodPressureModel>>>> {

            //make request for get latest diastolic measurements
            //collect the data and collect this data in new flow
            bloodPressureRepository
                .getDiastolicMeasurements().collect { status ->

                    //make condition on status result
                    when (status) {

                        //if is success
                        is Status.Success -> {

                            if (status.toData()?.statusCode == 200) {

                                //make new map for contain on sub mapper
                                val bloodPressureResult: MutableMap<String, Long> =
                                    emptyMap<String, Long>().toMutableMap()

                                var count = 0

                                for (key in (status.toData()?.body?.data
                                    ?: emptyMap()).keys.reversed()) {

                                    bloodPressureResult[key] =
                                        status.toData()?.body?.data!![key]!!

                                    count += 1
                                    if (count == 7) {
                                        break
                                    }//end if

                                }//end for

                                //map data from map data type to chart blood pressure model
                                val models =
                                    descBloodPressureDtoToChartBloodPressureModelMapper.listConvertor(
                                        list = bloodPressureResult
                                    ).reversed()

                                //emit data to flow
                                trySend(
                                    Status.Success(
                                        data = EffectResponse(
                                            statusCode = 200,
                                            body = models
                                        )
                                    )
                                )

                            }//end if
                            else {

                                //emit data to flow
                                trySend(
                                    Status.Success(
                                        data = EffectResponse(
                                            statusCode = status.toData()?.statusCode!!,
                                            body = emptyList()
                                        )
                                    )
                                )

                            }//end else

                        }//end success case

                        //if is error
                        is Status.Error -> {
                            trySend(status)
                        }//end error case

                        //if is loading
                        is Status.Loading -> {
                            trySend(status)
                        }//end loading case

                    }//end when

                }//end collect

        }.flowOn(Dispatchers.IO)//end flow

    }//end invoke

}//end GetLastWeekDiastolicRecordsUseCase