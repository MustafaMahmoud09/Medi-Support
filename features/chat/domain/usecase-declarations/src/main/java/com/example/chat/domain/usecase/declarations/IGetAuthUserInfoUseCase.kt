package com.example.chat.domain.usecase.declarations

import kotlinx.coroutines.flow.Flow

interface IGetProfileInfoUseCase {

    suspend operator fun invoke(): Flow<List<Long>>

}//end IGetProfileInfoUseCase