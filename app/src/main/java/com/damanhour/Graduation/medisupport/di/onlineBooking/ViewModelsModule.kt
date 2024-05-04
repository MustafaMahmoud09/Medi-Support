package com.damanhour.Graduation.medisupport.di.onlineBooking

import com.example.online.booking.domain.usecase.declarations.IGetPageOnlineBookingsUseCase
import com.example.online.booking.domain.usecase.declarations.IGetTopOnlineDoctorsUseCase
import com.example.online.booking.domain.usecase.declarations.IGetTotalOnlineDoctorsUseCase
import com.example.onlinebooking.presentation.uiState.viewModel.OnlineDetailsViewModel
import com.example.onlinebooking.presentation.uiState.viewModel.TopOnlineDoctorsViewModel
import com.example.onlinebooking.presentation.uiState.viewModel.TotalOnlineDoctorsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideTopOnlineDoctorsViewModel(
        getTopOnlineDoctorsUseCase: IGetTopOnlineDoctorsUseCase
    ): TopOnlineDoctorsViewModel {

        return TopOnlineDoctorsViewModel(
            getTopOnlineDoctorsUseCase = getTopOnlineDoctorsUseCase
        )

    }//end provideTopOfflineDoctorsViewModel


    @Provides
    fun provideTotalOnlineDoctorsViewModel(
        getTotalOnlineDoctorsUseCase: IGetTotalOnlineDoctorsUseCase
    ): TotalOnlineDoctorsViewModel {

        return TotalOnlineDoctorsViewModel(
            getTotalOnlineDoctorsUseCase = getTotalOnlineDoctorsUseCase
        )

    }//end provideTotalOfflineDoctorsViewModel


    @Provides
    fun provideOnlineDetailsViewModel(
        getPageOnlineBookingsUseCase: IGetPageOnlineBookingsUseCase
    ): OnlineDetailsViewModel{

        return OnlineDetailsViewModel(
            getPageOnlineBookingsUseCase = getPageOnlineBookingsUseCase
        )

    }//end provideOnlineDetailsViewModel


}//end ViewModelsModule