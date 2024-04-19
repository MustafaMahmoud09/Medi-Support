package com.damanhour.Graduation.medisupport.di

import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.libraries.core.remote.database.interceptors.AuthorizationInterceptor
import com.example.libraries.core.remote.database.interceptors.JsonFormatInterceptor
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
    @Named("retrofit_without_token")
    fun provideRetrofitWithoutToken(
        @Named("base_url") baseUrl: String,
        converter: GsonConverterFactory,
        @Named("time_out_client") timeOutClient: OkHttpClient,
        @Named("json_format_interceptor_client") jsonFormatClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
//            .client(timeOutClient)
            .client(jsonFormatClient)
            .addConverterFactory(converter)
            .build()

    }//end provideRetrofitWithoutToken


    @Provides
    @Singleton
    @Named("retrofit_with_token")
    fun provideRetrofitWithToken(
        @Named("base_url") baseUrl: String,
        converter: GsonConverterFactory,
        @Named("auth_interceptor_client") authInterceptorClient: OkHttpClient,
        @Named("time_out_client") timeOutClient: OkHttpClient,
        @Named("json_format_interceptor_client") jsonFormatClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .client(authInterceptorClient)
//            .client(timeOutClient)
            .client(jsonFormatClient)
            .build()

    }//end provideRetrofitWithToken


    @Provides
    @Singleton
    @Named("time_out_client")
    fun provideTimeOutClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .followRedirects(false)
            .build()

    }//end provideTimeOutClient


    @Provides
    @Singleton
    @Named("auth_interceptor_client")
    fun provideAuthInterceptorClient(
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
    @Named("json_format_interceptor_client")
    fun provideJsonFormatInterceptorClient(
        @Named("json_format_interceptor") interceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    }//end provideOkhttpClient

    @Provides
    @Singleton
    @Named("json_format_interceptor")
    fun provideJsonFormatInterceptor(): Interceptor {

        return JsonFormatInterceptor()

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

        return "https://3f0f-154-183-33-48.ngrok-free.app/api/"

    }//end provideBaseUrl


    @Provides
    @Singleton
    @Named("host")
    fun provideHost(): String {

        return "423d-197-63-203-14.ngrok-free.app"

    }//end provideBaseUrl


    @Provides
    @Singleton
    fun provideResponseWrapper(): ResponseWrapper {

        return ResponseWrapper()

    }//end provideResponseWrapper

}//end NetworkModule