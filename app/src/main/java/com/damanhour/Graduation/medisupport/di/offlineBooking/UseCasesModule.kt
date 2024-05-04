package com.damanhour.Graduation.medisupport.di.offlineBooking

import com.example.blood.sugar.domain.mapper.declarations.child.IOfflineDoctorDtoToOfflineDoctorModelMapper
import com.example.offline.booking.domain.mapper.declarations.child.IDateTimeDtoToTimeModelMapper
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineBookingEntityToOfflineBookingModelMapper
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.offline.booking.domain.usecase.declarations.IBookOfflineAppointmentUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetBookingTimeUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetOfflineDoctorDetailsByIdUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetPageOfflineBookingsUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetTopOfflineDoctorsUseCase
import com.example.offline.booking.domain.usecase.declarations.IGetTotalOfflineDoctorsUseCase
import com.example.offline.booking.domain.usecase.declarations.IRateOfflineDoctorUseCase
import com.example.offline.booking.domain.usecase.declarations.ISearchOnOfflineDoctorsUseCase
import com.example.offline.booking.domain.usecase.execution.BookOfflineAppointmentUseCase
import com.example.offline.booking.domain.usecase.execution.GetBookingTimeUseCase
import com.example.offline.booking.domain.usecase.execution.GetOfflineDoctorDetailsByIdUseCase
import com.example.offline.booking.domain.usecase.execution.GetPageOfflineBookingsUseCase
import com.example.offline.booking.domain.usecase.execution.GetTopOfflineDoctorsUseCase
import com.example.offline.booking.domain.usecase.execution.GetTotalOfflineDoctorsUseCase
import com.example.offline.booking.domain.usecase.execution.RateOfflineDoctorUseCase
import com.example.offline.booking.domain.usecase.execution.SearchOnOfflineDoctorsUseCase
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
    fun provideGetTopOfflineDoctorsUseCase(
        offlineBookingRepository: IOfflineBookingRepository,
        offlineDoctorDtoToOfflineDoctorModelMapper: IOfflineDoctorDtoToOfflineDoctorModelMapper
    ): IGetTopOfflineDoctorsUseCase {

        return GetTopOfflineDoctorsUseCase(
            offlineBookingRepository = offlineBookingRepository,
            offlineDoctorDtoToOfflineDoctorModelMapper = offlineDoctorDtoToOfflineDoctorModelMapper
        )

    }//end provideGetTopOfflineDoctorsUseCase


    @Provides
    @Singleton
    fun provideGetTotalOfflineDoctorsUseCase(
        offlineBookingRepository: IOfflineBookingRepository,
        offlineDoctorDtoToOfflineDoctorModelMapper: IOfflineDoctorDtoToOfflineDoctorModelMapper
    ): IGetTotalOfflineDoctorsUseCase {

        return GetTotalOfflineDoctorsUseCase(
            offlineBookingRepository = offlineBookingRepository,
            offlineDoctorDtoToOfflineDoctorModelMapper = offlineDoctorDtoToOfflineDoctorModelMapper
        )

    }//end provideGetTotalOfflineDoctorsUseCase


    @Provides
    @Singleton
    fun provideSearchOnOfflineDoctorsUseCase(
        offlineBookingRepository: IOfflineBookingRepository,
        offlineDoctorDtoToOfflineDoctorModelMapper: IOfflineDoctorDtoToOfflineDoctorModelMapper
    ): ISearchOnOfflineDoctorsUseCase {

        return SearchOnOfflineDoctorsUseCase(
            offlineBookingRepository = offlineBookingRepository,
            offlineDoctorDtoToOfflineDoctorModelMapper = offlineDoctorDtoToOfflineDoctorModelMapper
        )

    }//end provideSearchOnOfflineDoctorsUseCase


    //    getOfflineDoctorDetailsByIdUseCase: IGetOfflineDoctorDetailsByIdUseCase,
//    private val getBookingTimeUseCase: IGetBookingTimeUseCase
    @Provides
    @Singleton
    fun provideGetOfflineDoctorDetailsByIdUseCase(
        offlineBookingRepository: IOfflineBookingRepository,
        offlineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper: IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
    ): IGetOfflineDoctorDetailsByIdUseCase {

        return GetOfflineDoctorDetailsByIdUseCase(
            offlineBookingRepository = offlineBookingRepository,
            offlineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper = offlineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
        )

    }//end provideGetOfflineDoctorDetailsByIdUseCase


    @Provides
    @Singleton
    fun provideGetBookingTimeUseCase(
        offlineBookingRepository: IOfflineBookingRepository,
        dateTimeDtoToTimeModelMapper: IDateTimeDtoToTimeModelMapper
    ): IGetBookingTimeUseCase {

        return GetBookingTimeUseCase(
            offlineBookingRepository = offlineBookingRepository,
            dateTimeDtoToTimeModelMapper = dateTimeDtoToTimeModelMapper
        )

    }//end provideGetBookingTimeUseCase


    @Provides
    @Singleton
    fun provideBookOfflineAppointmentUseCase(
        offlineBookingRepository: IOfflineBookingRepository
    ): IBookOfflineAppointmentUseCase {

        return BookOfflineAppointmentUseCase(
            offlineBookingRepository = offlineBookingRepository
        )

    }//end provideBookOfflineAppointmentUseCase


    @Provides
    @Singleton
    fun provideRateOfflineDoctorUseCase(
        offlineBookingRepository: IOfflineBookingRepository,
    ): IRateOfflineDoctorUseCase {

        return RateOfflineDoctorUseCase(
            offlineBookingRepository = offlineBookingRepository
        )

    }//end provideRateOfflineDoctorUseCase


    @Provides
    @Singleton
    fun provideGetPageOfflineBookingsUseCase(
        offlineBookingRepository: IOfflineBookingRepository,
        offlineBookingEntityToOfflineBookingModelMapper: IOfflineBookingEntityToOfflineBookingModelMapper
    ): IGetPageOfflineBookingsUseCase {

        return GetPageOfflineBookingsUseCase(
            offlineBookingRepository = offlineBookingRepository,
            offlineBookingEntityToOfflineBookingModelMapper = offlineBookingEntityToOfflineBookingModelMapper
        )

    }//end provideGetPageOfflineBookingsUseCase

}//end UseCasesModule