package com.damanhour.Graduation.medisupport.di.offlineBooking

import com.example.blood.sugar.domain.mapper.declarations.child.IOfflineDoctorDtoToOfflineDoctorModelMapper
import com.example.offline.booking.domain.mapper.declarations.child.IDateTimeDtoToTimeModelMapper
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
import com.example.offline.booking.mapper.execution.DateTimeDtoToTimeModelMapper
import com.example.offline.booking.mapper.execution.OfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper
import com.example.offline.booking.mapper.execution.OfflineDoctorDtoToOfflineDoctorModelMapper
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
    fun provideOfflineDoctorDtoToOfflineDoctorModelMapper(
        @Named("domain") imageUrl: String
    ): IOfflineDoctorDtoToOfflineDoctorModelMapper {

        return OfflineDoctorDtoToOfflineDoctorModelMapper(
            baseImageUrl = imageUrl
        )

    }//end provideOfflineDoctorDtoToOfflineDoctorModelMapper


    @Provides
    @Singleton
    fun provideDateTimeDtoToTimeModelMapper()
            : IDateTimeDtoToTimeModelMapper = DateTimeDtoToTimeModelMapper()


    @Provides
    @Singleton
    fun provideOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper(
        @Named("domain") imageUrl: String
    ): IOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper{

        return OfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper(
            baseImageUrl = imageUrl
        )

    }//end provideOfflineDoctorDetailsDtoToOfflineDoctorDetailsModelMapper

}//end MappersModule