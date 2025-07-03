package com.damanhour.Graduation.medisupport.di.chat

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PusherModule {

    @Provides
    @Singleton
    @Named("cluster")
    fun providePusherCluster() = "eu"



    @Provides
    @Singleton
    @Named("pusher_key")
    fun providePusherKey() = "6b85a0675ed94f42b20c"



}//end ViewModelsModule