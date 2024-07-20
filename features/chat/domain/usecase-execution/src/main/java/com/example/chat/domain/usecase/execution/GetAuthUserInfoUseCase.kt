package com.example.chat.domain.usecase.execution

import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.IGetProfileInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalCoroutinesApi::class)
class GetProfileInfoUseCase(
    private val chatRepository: IChatRepository
) : IGetProfileInfoUseCase {

    //function for make request on repository for get profile data
    override suspend fun invoke(): Flow<List<Long>> {

        return channelFlow {

            //make request on repository for get user info
            chatRepository.getAccountInfo().collectLatest { userEntity ->
                //emit user model to chanel flow
                trySend(if (userEntity.isNotEmpty()) listOf(userEntity[0].id) else emptyList<Long>())
            }//end collectLatest

        }.flowOn(Dispatchers.IO)//end channelFlow

    }//end invoke

}//end GetProfileInfoUseCase