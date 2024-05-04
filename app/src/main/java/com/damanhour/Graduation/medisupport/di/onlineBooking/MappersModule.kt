package com.damanhour.Graduation.medisupport.di.onlineBooking

import com.example.online.booking.domain.mapper.declarations.child.IOnlineBookingDtoToOnlineBookingEntityMapper
import com.example.online.booking.domain.mapper.declarations.child.IOnlineBookingEntityToOnlineBookingModelMapper
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
import com.example.online.booking.domain.mapper.declarations.child.IOnlineDoctorDtoToOnlineDoctorModelMapper
import com.example.online.booking.mapper.execution.OnlineBookingDtoToOnlineBookingEntityMapper
import com.example.online.booking.mapper.execution.OnlineBookingEntityToOnlineBookingModelMapper
import com.example.online.booking.mapper.execution.OnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper
import com.example.online.booking.mapper.execution.OnlineDoctorDtoToOnlineDoctorModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideOnlineDoctorDtoToOnlineDoctorModelMapper(
        @Named("domain") imageUrl: String
    ): IOnlineDoctorDtoToOnlineDoctorModelMapper {

        return OnlineDoctorDtoToOnlineDoctorModelMapper(
            baseImageUrl = imageUrl
        )

    }//end provideOnlineDoctorDtoToOnlineDoctorModelMapper


    @Provides
    @Singleton
    fun provideOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper(
        @Named("domain") imageUrl: String
    ): IOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper {

        return OnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper(
            baseImageUrl = imageUrl
        )

    }//end provideOnlineDoctorDetailsDtoToOnlineDoctorDetailsModelMapper


    @Provides
    @Singleton
    fun provideOnlineBookingEntityToOnlineBookingModelMapper()
            : IOnlineBookingEntityToOnlineBookingModelMapper =
        OnlineBookingEntityToOnlineBookingModelMapper()


    @Provides
    @Singleton
    fun provideOnlineBookingDtoToOnlineBookingEntityMapper()
            : IOnlineBookingDtoToOnlineBookingEntityMapper =
        OnlineBookingDtoToOnlineBookingEntityMapper()

}//end MappersModule