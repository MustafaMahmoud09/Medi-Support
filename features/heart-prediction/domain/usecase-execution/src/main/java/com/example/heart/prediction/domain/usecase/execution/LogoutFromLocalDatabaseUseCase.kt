package com.example.heart.prediction.domain.usecase.execution

import com.example.heart.prediction.domain.repository.declarations.IHeartPredictionRepository
import com.example.heart.prediction.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase

class LogoutFromLocalDatabaseUseCase(
    private val heartPredictionRepository: IHeartPredictionRepository
): ILogoutFromLocalDatabaseUseCase {

    override suspend fun invoke() {

        heartPredictionRepository.logoutFromLocalDatabase()

    }//end invoke

}//end LogoutFromLocalDatabaseUseCase