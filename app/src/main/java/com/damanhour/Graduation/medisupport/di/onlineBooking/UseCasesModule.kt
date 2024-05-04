package com.damanhour.Graduation.medisupport.di.onlineBooking

import com.example.online.booking.domain.mapper.declarations.child.IOnlineBookingEntityToOnlineBookingModelMapper
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDtoToOnlineDoctorModelMapper
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.online.booking.domain.usecase.declarations.IBookOnlineAppointmentUseCase
import com.example.online.booking.domain.usecase.declarations.IGetOnlineDoctorDetailsByIdUseCase
import com.example.online.booking.domain.usecase.declarations.IGetPageOnlineBookingsUseCase
import com.example.online.booking.domain.usecase.declarations.IGetTopOnlineDoctorsUseCase
import com.example.online.booking.domain.usecase.declarations.IGetTotalOnlineDoctorsUseCase
import com.example.online.booking.domain.usecase.declarations.IRateOnlineDoctorUseCase
import com.example.online.booking.domain.usecase.execution.BookOnlineAppointmentUseCase
import com.example.online.booking.domain.usecase.execution.GetOnlineDoctorDetailsByIdUseCase
import com.example.online.booking.domain.usecase.execution.GetPageOnlineBookingsUseCase
import com.example.online.booking.domain.usecase.execution.GetTopOnlineDoctorsUseCase
import com.example.online.booking.domain.usecase.execution.GetTotalOnlineDoctorsUseCase
import com.example.online.booking.domain.usecase.execution.RateOnlineDoctorUseCase
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
    fun provideGetTopOnlineDoctorsUseCase(
        onlineBookingRepository: IOnlineBookingRepository,
        onlineDoctorDtoToOnlineDoctorModelMapper: IOnlineDoctorDtoToOnlineDoctorModelMapper
    ): IGetTopOnlineDoctorsUseCase {

        return GetTopOnlineDoctorsUseCase(
            onlineBookingRepository = onlineBookingRepository,
            onlineDoctorDtoToOnlineDoctorModelMapper = onlineDoctorDtoToOnlineDoctorModelMapper
        )

    }//end provideGetTopOnlineDoctorsUseCase


    @Provides
    @Singleton
    fun provideGetTotalOnlineDoctorsUseCase(
        onlineBookingRepository: IOnlineBookingRepository,
        onlineDoctorDtoToOnlineDoctorModelMapper: IOnlineDoctorDtoToOnlineDoctorModelMapper
    ): IGetTotalOnlineDoctorsUseCase {

        return GetTotalOnlineDoctorsUseCase(
            onlineBookingRepository = onlineBookingRepository,
            onlineDoctorDtoToOnlineDoctorModelMapper = onlineDoctorDtoToOnlineDoctorModelMapper
        )

    }//end provideGetTotalOnlineDoctorsUseCase


    @Provides
    @Singleton
    fun provideGetOnlineDoctorDetailsByIdUseCase(
        onlineBookingRepository: IOnlineBookingRepository,
        onlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper: IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
    ): IGetOnlineDoctorDetailsByIdUseCase {

        return GetOnlineDoctorDetailsByIdUseCase(
            onlineBookingRepository = onlineBookingRepository,
            onlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper = onlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
        )

    }//end provideGetOnlineDoctorDetailsByIdUseCase


    @Provides
    @Singleton
    fun provideBookOnlineAppointmentUseCase(
        onlineBookingRepository: IOnlineBookingRepository
    ): IBookOnlineAppointmentUseCase {

        return BookOnlineAppointmentUseCase(
            onlineBookingRepository = onlineBookingRepository
        )

    }//end provideBookOnlineAppointmentUseCase


    @Provides
    @Singleton
    fun provideRateOnlineDoctorUseCase(
        onlineBookingRepository: IOnlineBookingRepository
    ): IRateOnlineDoctorUseCase {

        return RateOnlineDoctorUseCase(
            onlineBookingRepository = onlineBookingRepository
        )

    }//end provideRateOnlineDoctorUseCase


    @Provides
    @Singleton
    fun provideGetPageOnlineBookingsUseCase(
        onlineBookingRepository: IOnlineBookingRepository,
        onlineBookingEntityToOnlineBookingModelMapper: IOnlineBookingEntityToOnlineBookingModelMapper
    ): IGetPageOnlineBookingsUseCase {

        return GetPageOnlineBookingsUseCase(
            onlineBookingRepository = onlineBookingRepository,
            onlineBookingEntityToOnlineBookingModelMapper = onlineBookingEntityToOnlineBookingModelMapper
        )

    }//end provideGetPageOnlineBookingsUseCase


}//end UseCasesModule