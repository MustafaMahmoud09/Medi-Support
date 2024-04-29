package com.damanhour.Graduation.medisupport.di

import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.libraries.core.remote.database.interceptors.AuthorizationInterceptor
import com.example.libraries.core.remote.database.interceptors.JsonFormatInterceptor
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {

        return GsonConverterFactory.create()

    }//end provideGsonConvertorFactory


    @Provides
    @Singleton
    @Named("domain")
    fun provideDomain(): String = "https://2e1a-154-183-40-118.ngrok-free.app/"


    @Provides
    @Singleton
    @Named("base_url")
    fun provideBaseUrl(
        @Named("domain") domain: String
    ): String = "${domain}api/"


    @Provides
    @Singleton
    @Named("json_format_interceptor")
    fun provideJsonFormatInterceptor(): Interceptor {

        return JsonFormatInterceptor()

    }//end provideAuthInterceptor


    @Provides
    @Singleton
    @Named("retrofit_without_token")
    fun provideRetrofitWithoutToken(
        @Named("base_url") baseUrl: String,
        converter: GsonConverterFactory,
        @Named("json_format_interceptor_client") jsonFormatClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(jsonFormatClient)
            .addConverterFactory(converter)
            .build()

    }//end provideRetrofitWithoutToken

    @Provides
    @Singleton
    @Named("json_format_interceptor_client")
    fun provideJsonFormatInterceptorClient(
        @Named("json_format_interceptor") interceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    }//end provideOkhttpClient


    @Provides
    @Singleton
    @Named("retrofit_with_token")
    fun provideRetrofitWithToken(
        @Named("base_url") baseUrl: String,
        converter: GsonConverterFactory,
        @Named("auth_interceptor_client") authInterceptorClient: OkHttpClient,
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(authInterceptorClient)
            .addConverterFactory(converter)
            .build()

    }//end provideRetrofitWithToken

    @Provides
    @Singleton
    @Named("auth_interceptor_client")
    fun provideAuthInterceptorClient(
        @Named("token_interceptor") authInterceptor: Interceptor,
        @Named("json_format_interceptor") jsonInterceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(jsonInterceptor)
            .build()

    }//end provideOkhttpClient

    @Provides
    @Singleton
    @Named("token_interceptor")
    fun provideAuthInterceptor(
        sharedPreferencesAccessObject: SharedPreferencesAccessObject
    ): Interceptor {

        return AuthorizationInterceptor(
            sharedPreferencesAccessObject = sharedPreferencesAccessObject
        )

    }//end provideAuthInterceptor


    @Provides
    @Singleton
    fun provideResponseWrapper(): ResponseWrapper {

        return ResponseWrapper()

    }//end provideResponseWrapper

}//end NetworkModule