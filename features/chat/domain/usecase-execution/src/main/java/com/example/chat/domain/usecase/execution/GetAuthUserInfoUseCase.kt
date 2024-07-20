package com.example.chat.domain.usecase.execution

import com.example.chat.domain.model.UserModel
import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.IGetAuthUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalCoroutinesApi::class)
class GetAuthUserInfoUseCase(
    private val chatRepository: IChatRepository
) : IGetAuthUserInfoUseCase {

    //function for make request on repository for get profile data
    override suspend fun invoke(): Flow<List<UserModel>> {

        return channelFlow {

            //make request on repository for get user info
            chatRepository.getAccountInfo().collectLatest { userEntity ->
                //emit user model to chanel flow
                trySend(if (userEntity.isNotEmpty()) listOf(
                    UserModel(
                        id = userEntity[0].id,
                        token = userEntity[0].token ?:""
                    )
                ) else emptyList<UserModel>())
            }//end collectLatest

        }.flowOn(Dispatchers.IO)//end channelFlow

    }//end invoke

}//end GetProfileInfoUseCase