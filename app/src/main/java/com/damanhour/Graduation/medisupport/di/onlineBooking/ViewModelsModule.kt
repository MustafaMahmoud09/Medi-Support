package com.damanhour.Graduation.medisupport.di.onlineBooking

import android.content.Context
import com.example.online.booking.domain.usecase.declarations.IGetPageOnlineBookingsUseCase
import com.example.online.booking.domain.usecase.declarations.IGetPaymentIntentSecretUseCase
import com.example.online.booking.domain.usecase.declarations.IGetTopOnlineDoctorsUseCase
import com.example.online.booking.domain.usecase.declarations.IGetTotalOnlineDoctorsUseCase
import com.example.onlinebooking.presentation.uiState.viewModel.OnlineDetailsViewModel
import com.example.onlinebooking.presentation.uiState.viewModel.TopOnlineDoctorsViewModel
import com.example.onlinebooking.presentation.uiState.viewModel.TotalOnlineDoctorsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        getPageOnlineBookingsUseCase: IGetPageOnlineBookingsUseCase,
        getPaymentIntentSecretUseCase: IGetPaymentIntentSecretUseCase,
        @ApplicationContext context: Context
    ): OnlineDetailsViewModel {

        return OnlineDetailsViewModel(
            getPageOnlineBookingsUseCase = getPageOnlineBookingsUseCase,
            applicationContext = context,
            getPaymentIntentSecretUseCase = getPaymentIntentSecretUseCase,
            paymentPublishedKey = "pk_test_51PFwYcEWuyFumFN8CuqDZh6dW2iZBG9PoV4rAHcfuUT63jLNurngeclmNZ2oDZ7fsQWqCYA0E31h05LyXgA5J9xw00y9AAjPWR",
        )

    }//end provideOnlineDetailsViewModel


}//end ViewModelsModule