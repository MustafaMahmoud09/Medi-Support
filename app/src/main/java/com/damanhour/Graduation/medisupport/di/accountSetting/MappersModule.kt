package com.damanhour.Graduation.medisupport.di.accountSetting

import com.example.account.setting.domain.mapper.declarations.child.IUserEntityToUserModelMapper
import com.example.account.setting.mapper.execution.UserEntityToUserModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideUserEntityToUserModelMapper(
        @Named("domain") imageUrl: String
    ): IUserEntityToUserModelMapper {

        return UserEntityToUserModelMapper(
            imageUrl = imageUrl
        )

    }//end provideUserEntityToUserModelMapper


}//end MappersModule