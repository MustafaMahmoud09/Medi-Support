package com.damanhour.Graduation.medisupport.di.accountSetting

import com.example.account.setting.data.source.remote.data.requests.AccountSettingRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestsModule {


    @Provides
    @Singleton
    fun provideAccountSettingRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): AccountSettingRequest {

        return retrofit.create(AccountSettingRequest::class.java)

    }//end provideAccountSettingRequest


}//end RequestsModule