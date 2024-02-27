package com.damanhour.Graduation.medisupport.di

import com.example.sharedui.uiState.viewModel.child.BottomNavigationViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    /**
     * function for provide single object from BottomNavigationViewModel to multi screen
     *
     * @return BottomNavigationViewModel
     * **/
    @Provides
    @Singleton
    fun provideBottomNavigationViewModel(): BottomNavigationViewModel {

        //provide bottom navigation view model here
        return BottomNavigationViewModel()

    }//end provideBottomNavigationViewModel


}//end ViewModelModule