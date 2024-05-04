package com.example.online.booking.domain.repository.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.online.booking.domain.dto.declarations.doctorDetails.IOnlineDoctorDetailsResponseDto
import com.example.online.booking.domain.dto.declarations.pageOnlineDoctor.IPageOnlineDoctorResponseDto
import com.example.online.booking.domain.dto.declarations.topOnlineDoctors.ITopOnlineDoctorResponseDto
import com.example.online.booking.domain.entity.declarations.IOnlineBookingEntity
import kotlinx.coroutines.flow.Flow

interface IOnlineBookingRepository {

    suspend fun getTopOnlineDoctors()
            : Flow<Status<EffectResponse<ITopOnlineDoctorResponseDto>>>


    suspend fun getPageOnlineDoctor(
        page: Int
    ): Flow<Status<EffectResponse<IPageOnlineDoctorResponseDto>>>



    suspend fun getDoctorDetails(
        doctorId: Long
    ): Flow<Status<EffectResponse<IOnlineDoctorDetailsResponseDto>>>



    suspend fun bookOnlineAppointment(
        doctorId: Long
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun rateDoctor(
        doctorId: Long,
        rate: Int
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun getPageOnlineBookings(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IOnlineBookingEntity>>


}//end IOfflineBookingRepository