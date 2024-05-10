package com.example.account.setting.domain.usecase.declarations

import com.example.account.setting.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface IGetProfileInfoUseCase {

    suspend operator fun invoke(): Flow<List<UserModel>>

}//end IGetProfileInfoUseCase