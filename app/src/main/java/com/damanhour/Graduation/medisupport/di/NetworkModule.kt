package com.damanhour.Graduation.medisupport.di

import com.example.remotedata.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    @Named("retrofit_without_token")
    fun provideRetrofitWithoutToken(
        @Named("base_url") baseUrl: String,
        converter: GsonConverterFactory
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .build()

    }//end provideRetrofitWithoutToken


    @Provides
    @Singleton
    @Named("retrofit_with_token")
    fun provideRetrofitWithToken(
        @Named("base_url") baseUrl: String,
        converter: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .client(okHttpClient)
            .build()

    }//end provideRetrofitWithToken


    @Provides
    @Singleton
    fun provideOkhttpClient(
        @Named("token_interceptor") interceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    }//end provideOkhttpClient


    @Provides
    @Singleton
    @Named("token_interceptor")
    fun provideAuthInterceptor(): Interceptor {

        return AuthorizationInterceptor()

    }//end provideAuthInterceptor


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {

        return GsonConverterFactory.create()

    }//end provideGsonConvertorFactory


    @Provides
    @Singleton
    @Named("base_url")
    fun provideBaseUrl(): String {

        return "https://156d-197-63-222-113.ngrok-free.app/api/"

    }//end provideBaseUrl

}//end NetworkModule