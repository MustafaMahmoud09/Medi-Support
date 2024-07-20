package com.example.heartrate.domain.usecase

import com.example.heart.rate.domain.repository.declarations.IHeartRateRepository
import com.example.heart.rate.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase

class LogoutFromLocalDatabaseUseCase(
    private val heartRateRepository: IHeartRateRepository,
): ILogoutFromLocalDatabaseUseCase {

    override suspend fun invoke() {

        heartRateRepository.logoutFromLocalDatabase()

    }//end invoke

}//end LogoutFromLocalDatabaseUseCase