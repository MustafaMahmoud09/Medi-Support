package com.damanhour.Graduation.medisupport.di.article

import com.example.article.data.source.remote.requests.ArticleRequest
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
    fun provideArticleRequest(
        @Named("retrofit_with_token") retrofit: Retrofit
    ): ArticleRequest {

        return retrofit.create(ArticleRequest::class.java)

    }//end provideArticleRequest


}//end RequestsModule