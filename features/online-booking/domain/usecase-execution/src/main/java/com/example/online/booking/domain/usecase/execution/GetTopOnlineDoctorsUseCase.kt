package com.example.online.booking.domain.usecase.execution

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.online.booking.domain.dto.declarations.IOnlineDoctorDto
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDtoToOnlineDoctorModelMapper
import com.example.online.booking.domain.model.OnlineDoctorModel
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.online.booking.domain.usecase.declarations.IGetTopOnlineDoctorsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetTopOnlineDoctorsUseCase(
    private val onlineBookingRepository: IOnlineBookingRepository,
    private val onlineDoctorDtoToOnlineDoctorModelMapper: IOnlineDoctorDtoToOnlineDoctorModelMapper
) : IGetTopOnlineDoctorsUseCase {

    //function for provide top offline doctors
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke()
            : Flow<Status<EffectResponse<List<OnlineDoctorModel>>>> {

        //return flow contain on response status
        return channelFlow<Status<EffectResponse<List<OnlineDoctorModel>>>> {

            //make request on repository for get top doctors
            onlineBookingRepository.getTopOnlineDoctors().collect { status ->

                when (status) {

                    //if status is success
                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get top doctors dto
                            val topDoctorsDto = status.toData()?.body?.data

                            //map data from dto to model here
                            val topDoctorModels =
                                onlineDoctorDtoToOnlineDoctorModelMapper.listConvertor(
                                    list = (topDoctorsDto
                                        ?: emptyList()) as List<IOnlineDoctorDto>
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