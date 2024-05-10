package com.example.account.setting.domain.usecase.execution

import com.example.account.setting.domain.mapper.declarations.child.IUserEntityToUserModelMapper
import com.example.account.setting.domain.model.UserModel
import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.account.setting.domain.usecase.declarations.IGetProfileInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalCoroutinesApi::class)
class GetProfileInfoUseCase(
    private val accountSettingRepository: IAccountSettingRepository,
    private val userEntityToUserModelMapper: IUserEntityToUserModelMapper
) : IGetProfileInfoUseCase {

    //function for make request on repository for get profile data
    override suspend fun invoke(): Flow<List<UserModel>> {

        return channelFlow {

            //make request on repository for get user info
            accountSettingRepository.getAccountInfo().collectLatest { userEntity ->
                //map user entity to user model here
                val userModel = userEntityToUserModelMapper.listConvertor(
                    list = userEntity
                )

                //emit user model to chanel flow
                trySend(userModel)
            }//end collectLatest

        }.flowOn(Dispatchers.IO)//end channelFlow

    }//end invoke

}//end GetProfileInfoUseCase