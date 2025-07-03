package com.example.chat.domain.usecase.declarations

import com.example.chat.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface IGetAuthUserInfoUseCase {

    suspend operator fun invoke(): Flow<List<UserModel>>

}//end IGetProfileInfoUseCase