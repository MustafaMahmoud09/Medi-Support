package com.damanhour.Graduation.medisupport.di.accountSetting

import com.example.account.setting.domain.mapper.declarations.child.IUserEntityToUserModelMapper
import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.account.setting.domain.usecase.declarations.IDeleteAccountUseCase
import com.example.account.setting.domain.usecase.declarations.IGetProfileInfoUseCase
import com.example.account.setting.domain.usecase.declarations.ILogoutUseCase
import com.example.account.setting.domain.usecase.declarations.ISendContactUsMessageUseCase
import com.example.account.setting.domain.usecase.declarations.IUpdateProfileInfoUseCase
import com.example.account.setting.domain.usecase.execution.DeleteAccountUseCase
import com.example.account.setting.domain.usecase.execution.GetProfileInfoUseCase
import com.example.account.setting.domain.usecase.execution.LogoutUseCase
import com.example.account.setting.domain.usecase.execution.SendContactUsMessageUseCase
import com.example.account.setting.domain.usecase.execution.UpdateProfileInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {


    @Provides
    @Singleton
    fun provideUpdateProfileInfoUseCase(
        accountSettingRepository: IAccountSettingRepository
    ): IUpdateProfileInfoUseCase {

        return UpdateProfileInfoUseCase(
            accountSettingRepository = accountSettingRepository
        )

    }//end provideUpdateProfileInfoUseCase


    @Provides
    @Singleton
    fun provideGetProfileInfoUseCase(
        accountSettingRepository: IAccountSettingRepository,
        userEntityToUserModelMapper: IUserEntityToUserModelMapper
    ): IGetProfileInfoUseCase {

        return GetProfileInfoUseCase(
            accountSettingRepository = accountSettingRepository,
            userEntityToUserModelMapper = userEntityToUserModelMapper
        )

    }//end provideGetProfileInfoUseCase


    @Provides
    @Singleton
    fun provideSendContactUsMessageUseCase(
        accountSettingRepository: IAccountSettingRepository
    ): ISendContactUsMessageUseCase {

        return SendContactUsMessageUseCase(
            accountSettingRepository = accountSettingRepository
        )

    }//end provideSendContactUsMessageUseCase


    @Provides
    @Singleton
    fun provideDeleteAccountUseCase(
        accountSettingRepository: IAccountSettingRepository
    ): IDeleteAccountUseCase {

        return DeleteAccountUseCase(
            accountSettingRepository = accountSettingRepository
        )

    }//end provideDeleteAccountUseCase


    @Provides
    @Singleton
    fun provideLogoutUseCase(
        accountSettingRepository: IAccountSettingRepository
    ): ILogoutUseCase{

        return LogoutUseCase(
            accountSettingRepository = accountSettingRepository
        )

    }//end provideLogoutUseCase

}//end UseCasesModule