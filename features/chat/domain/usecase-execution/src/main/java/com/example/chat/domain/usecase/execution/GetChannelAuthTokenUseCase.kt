@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.chat.domain.usecase.execution

import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.IGetChannelAuthTokenUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class GetChannelAuthTokenUseCase(
    private val chatRepository: IChatRepository,
): IGetChannelAuthTokenUseCase{

    override suspend operator fun invoke(
        socketId: String,
        channelName: String
    ): Flow<Status<EffectResponse<String>>>{

        //return flow contain on response status
        return channelFlow<Status<EffectResponse<String>>> {

            //make request on repository for get top doctors
            chatRepository.getChatAuthToken(
                socketId = socketId,
                channelName = channelName
            ).collect { status ->

                when (status) {

                    //if status is success
                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get top doctors dto
                            val channelDetails = status.toData()?.body


                            //send top doctor models here
                            trySend(
                                element = Status.Success(
                                    data = EffectResponse(
                                        statusCode = status.toData()?.statusCode!!,
                                        body = channelDetails?.auth
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
                                        body = null
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

}//end IGetChatsUseCase