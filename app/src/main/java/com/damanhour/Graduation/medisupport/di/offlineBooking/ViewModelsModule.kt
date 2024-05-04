package com.damanhour.Graduation.medisupport.di.offlineBooking

import com.example.offline.booking.domain.usecase.declarations.IGetPageOfflineBookingsUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetTopOfflineDoctorsUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetTotalOfflineDoctorsUseCase
import com.example.offline.booking.domain.usecase.declarations.ISearchOnOfflineDoctorsUseCase
import com.example.offlinebooking.presentation.uiState.viewModel.OfflineDetailsViewModel
import com.example.offlinebooking.presentation.uiState.viewModel.SearchViewModel
import com.example.offlinebooking.presentation.uiState.viewModel.TopOfflineDoctorsViewModel
import com.example.offlinebooking.presentation.uiState.viewModel.TotalOfflineDoctorsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideTopOfflineDoctorsViewModel(
        getTopOfflineDoctorsUseCase: IGetTopOfflineDoctorsUseCase
    ): TopOfflineDoctorsViewModel {

        return TopOfflineDoctorsViewModel(
            getTopOfflineDoctorsUseCase = getTopOfflineDoctorsUseCase
        )

    }//end provideTopOfflineDoctorsViewModel


    @Provides
    fun provideTotalOfflineDoctorsViewModel(
        getTotalOfflineDoctorsUseCase: IGetTotalOfflineDoctorsUseCase
    ): TotalOfflineDoctorsViewModel {

        return TotalOfflineDoctorsViewModel(
            getTotalOfflineDoctorsUseCase = getTotalOfflineDoctorsUseCase
        )

    }//end provideTotalOfflineDoctorsViewModel


    @Provides
    @Singleton
    fun provideSearchViewModel(
        searchOnOfflineDoctorsUseCase: ISearchOnOfflineDoctorsUseCase,
    ): SearchViewModel {

        return SearchViewModel(
            searchOnOfflineDoctorsUseCase = searchOnOfflineDoctorsUseCase
        )

    }//end provideSearchViewModel


    @Provides
    fun provideOfflineDetailsViewModel(
        getPageOfflineBookingsUseCase: IGetPageOfflineBookingsUseCase
    ): OfflineDetailsViewModel {

        return OfflineDetailsViewModel(
            getPageOfflineBookingsUseCase = getPageOfflineBookingsUseCase
        )

    }//end provideOfflineDetailsViewModel


}//end ViewModelsModule