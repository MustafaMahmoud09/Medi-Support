package com.example.account.setting.domain.usecase.execution

import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.account.setting.domain.usecase.declarations.ILogoutFromLocalDatabaseUseCase

class LogoutFromLocalDatabaseUseCase(
    private val accountSettingRepository: IAccountSettingRepository,
): ILogoutFromLocalDatabaseUseCase {

    override suspend fun invoke() {

        accountSettingRepository.logoutFromLocalDatabase()

    }//end invoke

}//end LogoutFromLocalDatabaseUseCase