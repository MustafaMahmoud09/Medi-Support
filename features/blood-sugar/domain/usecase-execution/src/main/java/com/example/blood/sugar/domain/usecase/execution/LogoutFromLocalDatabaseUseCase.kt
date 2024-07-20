package com.example.blood.sugar.domain.usecase.execution

import com.example.blood.sugar.domain.repository.declarations.IBloodSugarRepository
import com.example.blood.sugar.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase

class LogoutFromLocalDatabaseUseCase(
    private val bloodSugarRepository: IBloodSugarRepository,
): ILogoutFromLocalDatabaseUseCase {

    override suspend fun invoke() {

        bloodSugarRepository.logoutFromLocalDatabase()

    }//end invoke

}//end LogoutFromLocalDatabaseUseCase