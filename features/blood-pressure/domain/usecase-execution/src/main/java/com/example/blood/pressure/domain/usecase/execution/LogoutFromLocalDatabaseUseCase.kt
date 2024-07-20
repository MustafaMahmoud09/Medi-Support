package com.example.blood.pressure.domain.usecase.execution

import com.example.blood.pressure.domain.repository.declarations.IBloodPressureRepository
import com.example.blood.pressure.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase

class LogoutFromLocalDatabaseUseCase(
    private val bloodPressureRepository: IBloodPressureRepository,
): ILogoutFromLocalDatabaseUseCase {

    override suspend fun invoke() {

        bloodPressureRepository.logoutFromLocalDatabase()

    }//end invoke

}//end LogoutFromLocalDatabaseUseCase