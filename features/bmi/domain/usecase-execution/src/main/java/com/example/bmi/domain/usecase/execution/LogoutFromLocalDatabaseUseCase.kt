package com.example.bmi.domain.usecase.execution

import com.example.bmi.domain.repository.declarations.IBMIRepository
import com.example.bmi.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase

class LogoutFromLocalDatabaseUseCase(
    private val bmiRepository: IBMIRepository,
): ILogoutFromLocalDatabaseUseCase {

    override suspend fun invoke() {

        bmiRepository.logoutFromLocalDatabase()

    }//end invoke

}//end LogoutFromLocalDatabaseUseCase