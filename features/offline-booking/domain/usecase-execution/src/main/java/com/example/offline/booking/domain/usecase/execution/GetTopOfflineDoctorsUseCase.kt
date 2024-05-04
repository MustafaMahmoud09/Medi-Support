package com.example.offline.booking.domain.usecase.execution

import com.example.blood.sugar.domain.mapper.declarations.child.IOfflineDoctorDtoToOfflineDoctorModelMapper
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.offline.booking.domain.dto.declarations.IOfflineDoctorDto
import com.example.offline.booking.domain.model.OfflineDoctorModel
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.offline.booking.domain.usecase.declarations.IGetTopOfflineDoctorsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetTopOfflineDoctorsUseCase(
    private val offlineBookingRepository: IOfflineBookingRepository,
    private val offlineDoctorDtoToOfflineDoctorModelMapper: IOfflineDoctorDtoToOfflineDoctorModelMapper
) : IGetTopOfflineDoctorsUseCase {

    //function for provide top offline doctors
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke()
            : Flow<Status<EffectResponse<List<OfflineDoctorModel>>>> {

        //return flow contain on response status
        return channelFlow<Status<EffectResponse<List<OfflineDoctorModel>>>> {

            //make request on repository for get top doctors
            offlineBookingRepository.getTopOfflineDoctors().collect { status ->

                when (status) {

                    //if status is success
                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get top doctors dto
                            val topDoctorsDto = status.toData()?.body?.data

                            //map data from dto to model here
                            val topDoctorModels =
                                offlineDoctorDtoToOfflineDoctorModelMapper.listConvertor(
                                    list = (topDoctorsDto
                                        ?: emptyList()) as List<IOfflineDoctorDto>
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

}//end GetTopOfflineDoctorsUseCase