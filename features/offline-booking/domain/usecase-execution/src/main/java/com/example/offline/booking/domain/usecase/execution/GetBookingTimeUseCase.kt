package com.example.offline.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.dto.declarations.doctorTime.ITime
import com.example.offline.booking.domain.mapper.declarations.child.IDateTimeDtoToTimeModelMapper
import com.example.offline.booking.domain.model.TimeModel
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.offline.booking.domain.usecase.declarations.IGetBookingTimeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalCoroutinesApi::class)
class GetBookingTimeUseCase(
    private val offlineBookingRepository: IOfflineBookingRepository,
    private val dateTimeDtoToTimeModelMapper: IDateTimeDtoToTimeModelMapper
) : IGetBookingTimeUseCase{

    override suspend fun invoke(
        dateId: Long
    ): Flow<Status<EffectResponse<List<TimeModel>>>> {

        //return flow contain on response status
        return channelFlow<Status<EffectResponse<List<TimeModel>>>> {

            //make request on repository for get top doctors
            offlineBookingRepository.getDateTimes(
                dateId  = dateId
            ).collect { status ->

                when (status) {

                    //if status is success
                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get top doctors dto
                            val timesDto = status.toData()?.body?.data

                            //map data from dto to model here
                            val topDoctorModels =
                                dateTimeDtoToTimeModelMapper.listConvertor(
                                    list = (timesDto ?: emptyList()) as List<ITime>
                                )

                            //send top doctor models here
                            trySend(
                                element = Status.Success(
                                    data = EffectResponse(
                                        statusCode = status.toData()?.statusCode!!,
                                        body = topDoctorModels
                                    )
                                )
                            )

                        }//end if
                        else {

                            //return empty list with error status
                            trySend(
                                element = Status.Success(
                                    data = EffectResponse(
                                        statusCode = status.toData()?.statusCode!!,
                                        body = emptyList()
                                    )
                                )
                            )

                        }//end else

                    }//end success case

                    //if status is loading
                    is Status.Loading -> {
                        trySend(status)
                    }//end loading case

                    //if status is error
                    is Status.Error -> {
                        trySend(status)
                    }//end error case

                }//end when

            }//end collect

        }.flowOn(Dispatchers.IO)

    }//end invoke

}//end GetBookingTimeUseCase