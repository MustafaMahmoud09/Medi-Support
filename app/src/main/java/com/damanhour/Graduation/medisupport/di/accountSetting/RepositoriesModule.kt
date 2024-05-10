package com.damanhour.Graduation.medisupport.di.accountSetting

import android.content.Context
import com.example.account.setting.data.repository.execution.AccountSettingRepositoryImpl
import com.example.account.setting.data.repository.execution.cacheHelperDeclarations.ICacheAccountRepositoryHelper
import com.example.account.setting.data.repository.execution.cacheHelperDeclarations.IServerAccountRepositoryHelper
import com.example.account.setting.data.repository.execution.cacheHelperExecution.CacheAccountRepositoryHelper
import com.example.account.setting.data.repository.execution.cacheHelperExecution.ServerAccountRepositoryHelper
import com.example.account.setting.data.source.remote.data.requests.AccountSettingRequest
import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideAccountSettingRepository(
        wrapper: ResponseWrapper,
        accountSettingRequest: AccountSettingRequest,
        serverAccountRepositoryHelper: IServerAccountRepositoryHelper,
        localDatabase: MediSupportDatabase,
        sharedPreferencesAccessObject: SharedPreferencesAccessObject,
        cacheAccountRepositoryHelper: ICacheAccountRepositoryHelper,
        @ApplicationContext context: Context
    ): IAccountSettingRepository {

        return AccountSettingRepositoryImpl(
            wrapper = wrapper,
            accountSettingRequest = accountSettingRequest,
            serverAccountRepositoryHelper = serverAccountRepositoryHelper,
            sharedPreferencesAccessObject = sharedPreferencesAccessObject,
            localDatabase = localDatabase,
            cacheAccountRepositoryHelper = cacheAccountRepositoryHelper,
            context = context
        )

    }//end provideOfflineBookingRepository


    @Provides
    @Singleton
    fun provideServerAccountRepositoryHelper(
        accountSettingRequest: AccountSettingRequest,
        wrapper: ResponseWrapper,
        cacheAccountRepositoryHelper: ICacheAccountRepositoryHelper,
    ): IServerAccountRepositoryHelper {

        return ServerAccountRepositoryHelper(
            wrapper = wrapper,
            accountSettingRequest = accountSettingRequest,
            cacheAccountRepositoryHelper = cacheAccountRepositoryHelper
        )

    }//end provideServerOfflineBookingRepositoryHelper


    @Provides
    @Singleton
    fun provideCacheAccountRepositoryHelper(
        localDatabase: MediSupportDatabase
    ): ICacheAccountRepositoryHelper {

        return CacheAccountRepositoryHelper(
            localDatabase = localDatabase
        )

    }//end provideCacheAccountRepositoryHelper

}//end RepositoriesModule