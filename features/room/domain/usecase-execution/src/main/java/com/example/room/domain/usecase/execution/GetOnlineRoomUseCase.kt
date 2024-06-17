@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.room.domain.usecase.execution

import com.example.domain_model.OnlineRoomModel
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.room.domain.dto.declarations.roomToken.IOnlineRoomDto
import com.example.room.domain.mapper.declarations.child.IOnlineRoomDtoToOnlineRoomModelMapper
import com.example.room.domain.repository.declarations.IOnlineRoomRepository
import com.example.room.domain.usecase.declarations.IGetOnlineRoomUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetOnlineRoomUseCase(
    private val onlineRoomRepository: IOnlineRoomRepository,
    private val onlineRoomDtoToOnlineRoomModelMapper: IOnlineRoomDtoToOnlineRoomModelMapper
) : IGetOnlineRoomUseCase {

    //function for make request on repository for get online room token
    override suspend fun invoke(
        bookingId: Long
    ): Flow<Status<EffectResponse<List<OnlineRoomModel>>>> {

        return channelFlow<Status<EffectResponse<List<OnlineRoomModel>>>> {

            onlineRoomRepository.getOnlineRoomToken(
                bookingId = bookingId
            ).collect { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            val onlineRoomDto = status.toData()?.body?.data

                            val onlineRoomModel = onlineRoomDtoToOnlineRoomModelMapper.listConvertor(
                                list = listOf(onlineRoomDto as IOnlineRoomDto)
                            )

                            trySend(
                                element = Status.Success(
                                    EffectResponse(
                                        statusCode = status.toData()!!.statusCode,
                                        body = onlineRoomModel
                                    )
                                )
                            )

                        }//end if

                        else {

                            trySend(
                                element = Status.Success(
                                    EffectResponse(
                                        statusCode = status.toData()!!.statusCode,
                                        body = null
                                    )
                                )
                            )

                        }//else

                    }//end success case

                    is Status.Loading -> {
                        trySend(status)
                    }//end loading case

                    is Status.Error -> {
                        trySend(status)
                    }//end error case

                }//end when

            }//end

        }.flowOn(Dispatchers.IO)//end channelFlow

    }//end invoke

}//end GetOnlineRoomUseCase