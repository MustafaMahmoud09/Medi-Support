package com.example.offline.booking.domain.repository.declarations

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.offline.booking.domain.dto.declarations.doctorDetails.IDoctorDetailsResponseDto
import com.example.offline.booking.domain.dto.declarations.doctorTime.IDoctorTimeResponseDto
import com.example.offline.booking.domain.dto.declarations.pageOfflineDoctors.IPageOfflineDoctorsResponseDto
import com.example.offline.booking.domain.dto.declarations.topOfflineDoctors.ITopOfflineDoctorsResponseDto
import com.example.offline.booking.domain.entity.declarations.IOfflineBookingEntity
import kotlinx.coroutines.flow.Flow

interface IOfflineBookingRepository {

    suspend fun getTopOfflineDoctors()
            : Flow<Status<EffectResponse<ITopOfflineDoctorsResponseDto>>>


    suspend fun getPageOfflineDoctor(
        page: Int
    ): Flow<Status<EffectResponse<IPageOfflineDoctorsResponseDto>>>


    suspend fun searchOnOfflineDoctors(
        searchKey: String,
        page: Int
    ): Flow<Status<EffectResponse<IPageOfflineDoctorsResponseDto>>>


    suspend fun getDoctorDetails(
        doctorId: Long
    ): Flow<Status<EffectResponse<IDoctorDetailsResponseDto>>>


    suspend fun getDateTimes(
        dateId: Long
    ): Flow<Status<EffectResponse<IDoctorTimeResponseDto>>>


    suspend fun bookOfflineAppointment(
        dateId: Long,
        timeId: Long,
        doctorId: Long
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun rateDoctor(
        doctorId: Long,
        rate: Int
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun getPageOfflineBookings(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IOfflineBookingEntity>>

}//end IOfflineBookingRepository