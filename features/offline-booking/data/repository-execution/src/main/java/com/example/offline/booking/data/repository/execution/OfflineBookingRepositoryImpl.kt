package com.example.offline.booking.data.repository.execution

import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.offline.bookin.data.source.remote.data.requests.OfflineBookingRequest
import com.example.offline.bookin.data.source.remote.data.requests.OfflineDoctorsRequest
import com.example.offline.booking.data.repository.execution.cacheHelperDeclarations.IServerOfflineBookingRepositoryHelper
import com.example.offline.booking.data.source.remote.data.dto.execution.doctorDetails.DoctorDetailsResponseDto
import com.example.offline.booking.data.source.remote.data.dto.execution.doctorTime.DoctorTimeResponseDto
import com.example.offline.booking.domain.dto.declarations.pageOfflineDoctors.IPageOfflineDoctorsResponseDto
import com.example.offline.booking.data.source.remote.data.dto.execution.pageOfflineDoctors.PageOfflineDoctorsResponseDto
import com.example.offline.booking.domain.dto.declarations.topOfflineDoctors.ITopOfflineDoctorsResponseDto
import com.example.offline.booking.data.source.remote.data.dto.execution.topOfflineDoctors.TopOfflineDoctorsResponseDto
import com.example.offline.booking.domain.dto.declarations.doctorDetails.IDoctorDetailsResponseDto
import com.example.offline.booking.domain.dto.declarations.doctorTime.IDoctorTimeResponseDto
import com.example.offline.booking.domain.entity.declarations.IOfflineBookingEntity
import com.example.offline.booking.domain.repository.declarations.IOfflineBookingRepository
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.flow.Flow

class OfflineBookingRepositoryImpl(
    private val offlineDoctorsRequest: OfflineDoctorsRequest,
    private val offlineBookingRequest: OfflineBookingRequest,
    private val wrapper: ResponseWrapper,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject,
    private val serverOfflineBookingRepositoryHelper: IServerOfflineBookingRepositoryHelper,
    private val localDatabase: MediSupportDatabase
) : IOfflineBookingRepository {

    //function for make request for get top offline doctors from server
    override suspend fun getTopOfflineDoctors()
            : Flow<Status<EffectResponse<ITopOfflineDoctorsResponseDto>>> {

        return wrapper.infiniteWrapper<ITopOfflineDoctorsResponseDto, TopOfflineDoctorsResponseDto> {
            offlineDoctorsRequest.getTopOfflineDoctors()
        }//end infiniteWrapper

    }//end getTopOfflineDoctors


    //function for provide page contain on 10 offline doctors
    override suspend fun getPageOfflineDoctor(
        page: Int
    ): Flow<Status<EffectResponse<IPageOfflineDoctorsResponseDto>>> {

        return wrapper.infiniteWrapper<IPageOfflineDoctorsResponseDto, PageOfflineDoctorsResponseDto> {
            offlineDoctorsRequest.getAllOfflineDoctors(
                page = page
            )
        }//end infiniteWrapper

    }//end getPageOfflineDoctor


    //function for make search on offline doctor on server
    override suspend fun searchOnOfflineDoctors(
        searchKey: String,
        page: Int
    ): Flow<Status<EffectResponse<IPageOfflineDoctorsResponseDto>>> {

        return wrapper.infiniteWrapper<IPageOfflineDoctorsResponseDto, PageOfflineDoctorsResponseDto> {
            offlineDoctorsRequest.searchOnOfflineDoctors(
                page = page,
                searchKey = searchKey
            )
        }//end infiniteWrapper

    }//end searchOnOfflineDoctors


    //function for make request on server for get doctor details
    override suspend fun getDoctorDetails(
        doctorId: Long
    ): Flow<Status<EffectResponse<IDoctorDetailsResponseDto>>> {

        return wrapper.infiniteWrapper<IDoctorDetailsResponseDto, DoctorDetailsResponseDto> {
            offlineBookingRequest.getDoctorDetails(
                id = doctorId
            )
        }//end infiniteWrapper

    }//end getDoctorDetails


    //function for make request on server for get booking date times
    override suspend fun getDateTimes(
        dateId: Long
    ): Flow<Status<EffectResponse<IDoctorTimeResponseDto>>> {

        return wrapper.infiniteWrapper<IDoctorTimeResponseDto, DoctorTimeResponseDto> {
            offlineBookingRequest.getDateTimes(
                id = dateId
            )
        }//end infiniteWrapper

    }//end getDateTimes


    //function for make request on server for book offline appointment
    override suspend fun bookOfflineAppointment(
        dateId: Long,
        timeId: Long,
        doctorId: Long
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            offlineBookingRequest.bookOfflineAppointment(
                dateId = dateId,
                timeId = timeId,
                doctorId = doctorId
            )
        }//end wrapper

    }//end bookOfflineAppointment


    //function for make request on server for rate doctor
    override suspend fun rateDoctor(
        doctorId: Long,
        rate: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            offlineDoctorsRequest.rateDoctor(
                doctorId = doctorId,
                rate = rate
            )
        }//end wrapper

    }//end rateDoctor


    //function for get page contain on online bookings
    override suspend fun getPageOfflineBookings(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IOfflineBookingEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //get records from server and store this records in local
        val lastPage = serverOfflineBookingRepositoryHelper.getPageOfflineBookingRecordsFromSever(
            userAuthId = userAuthId,
            page = page,
            pageSize = pageSize
        )

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.offlineBookingDao().selectPageOfflineBooking(
                page = page,
                pageSize = pageSize,
                userId = userAuthId
            )
        )

    }//end getPageOnlineBookings


}//end OfflineBookingRepositoryImpl