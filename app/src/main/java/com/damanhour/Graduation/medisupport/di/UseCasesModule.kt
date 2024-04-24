package com.damanhour.Graduation.medisupport.di

import com.example.article.domain.useCase.GetPageArticlesUseCase
import com.example.libraries.shared.logic.usecase.execution.GetMonthDaysUseCase
import com.example.libraries.shered.logic.usecase.declarations.IGetMonthDaysUseCase
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
    fun provideGetPageArticlesUseCase()
            : IGetMonthDaysUseCase = GetMonthDaysUseCase()


}//end UseCasesModule