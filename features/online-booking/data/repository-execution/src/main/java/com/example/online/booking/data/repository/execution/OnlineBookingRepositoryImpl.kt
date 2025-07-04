package com.example.online.booking.data.repository.execution

import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.online.booking.data.repository.execution.cacheHelperDeclarations.IServerOnlineBookingRepositoryHelper
import com.example.online.booking.data.source.remote.data.dto.execution.doctorDetails.OnlineDoctorDetailsResponseDto
import com.example.online.booking.data.source.remote.data.dto.execution.pageOnlineDoctor.PageOnlineDoctorResponseDto
import com.example.online.booking.data.source.remote.data.dto.execution.payment.PaymentResponseDto
import com.example.online.booking.data.source.remote.data.dto.execution.topOnlineDoctors.TopOnlineDoctorResponseDto
import com.example.online.booking.data.source.remote.data.requests.OnlineBookingRequest
import com.example.online.booking.data.source.remote.data.requests.OnlineDoctorsRequest
import com.example.online.booking.domain.dto.declarations.doctorDetails.IOnlineDoctorDetailsResponseDto
import com.example.online.booking.domain.dto.declarations.pageOnlineDoctor.IPageOnlineDoctorResponseDto
import com.example.online.booking.domain.dto.declarations.payment.IPaymentResponseDto
import com.example.online.booking.domain.dto.declarations.topOnlineDoctors.ITopOnlineDoctorResponseDto
import com.example.online.booking.domain.entity.declarations.IOnlineBookingEntity
import com.example.online.booking.domain.repository.declarations.IOnlineBookingRepository
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

class OnlineBookingRepositoryImpl(
    private val localDatabase: MediSupportDatabase,
    private val onlineDoctorsRequest: OnlineDoctorsRequest,
    private val onlineBookingRequest: OnlineBookingRequest,
    private val wrapper: ResponseWrapper,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject,
    private val serverOnlineBookingRepositoryHelper: IServerOnlineBookingRepositoryHelper
) : IOnlineBookingRepository {

    //function for make request for get top offline doctors from server
    override suspend fun getTopOnlineDoctors()
            : Flow<Status<EffectResponse<ITopOnlineDoctorResponseDto>>> {

        return wrapper.infiniteWrapper<ITopOnlineDoctorResponseDto, TopOnlineDoctorResponseDto> {
            onlineDoctorsRequest.getTopOnlineDoctors()
        }//end infiniteWrapper

    }//end getTopOfflineDoctors


    //function for provide page contain on 10 offline doctors
    override suspend fun getPageOnlineDoctor(
        page: Int
    ): Flow<Status<EffectResponse<IPageOnlineDoctorResponseDto>>> {

        return wrapper.infiniteWrapper<IPageOnlineDoctorResponseDto, PageOnlineDoctorResponseDto> {
            onlineDoctorsRequest.getAllOnlineDoctors(
                page = page
            )
        }//end infiniteWrapper

    }//end getPageOfflineDoctor


    //function for make request on server for get doctor details
    override suspend fun getDoctorDetails(
        doctorId: Long
    ): Flow<Status<EffectResponse<IOnlineDoctorDetailsResponseDto>>> {

        return wrapper.infiniteWrapper<IOnlineDoctorDetailsResponseDto, OnlineDoctorDetailsResponseDto> {
            onlineBookingRequest.getDoctorDetails(
                id = doctorId
            )
        }//end infiniteWrapper

    }//end getDoctorDetails


    //function for make request on server for book offline appointment
    override suspend fun bookOnlineAppointment(
        doctorId: Long
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            onlineBookingRequest.bookOnlineAppointment(
                doctorId = doctorId
            )
        }//end wrapper

    }//end bookOnlineAppointment


    //function for make request on server for rate doctor
    override suspend fun rateDoctor(
        doctorId: Long,
        rate: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            onlineDoctorsRequest.rateDoctor(
                doctorId = doctorId,
                rate = rate
            )
        }//end wrapper

    }//end rateDoctor


    //function for get page contain on online bookings
    override suspend fun getPageOnlineBookings(
        page: Int,
        pageSize: Int
    ): UnEffectResponse<List<IOnlineBookingEntity>> {

        //get user auth id
        val userAuthId = localDatabase.userDao().selectUserByAccessToken(
            token = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()
        ).id

        //get records from server and store this records in local
        val lastPage = serverOnlineBookingRepositoryHelper.getPageOnlineBookingRecordsFromSever(
            userAuthId = userAuthId,
            page = page,
            pageSize = pageSize
        )

        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = localDatabase.onlineBookingDao().selectPageOnlineBooking(
                page = page,
                pageSize = pageSize,
                userId = userAuthId
            )
        )

    }//end getPageOnlineBookings


    //function for make request on server for get payment intent secret
    override suspend fun getPaymentIntent(
        bookingId: Long
    ): Flow<Status<EffectResponse<IPaymentResponseDto>>> {

        return channelFlow {

            wrapper.wrapper<IPaymentResponseDto, PaymentResponseDto> {
                onlineBookingRequest.getPaymentIntentSecret(
                    id = bookingId
                )
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {
                        if (status.toData()?.statusCode == 422) {
                            localDatabase.onlineBookingDao().updateOnlineBookingStatusById(
                                id = bookingId,
                                status = 2
                            )
                        }//end if
                        if (status.toData()?.statusCode == 421) {
                            localDatabase.onlineBookingDao().updateOnlineBookingStatusById(
                                id = bookingId,
                                status = 0
                            )
                        }//end if
                        if (status.toData()?.statusCode == 423) {
                            localDatabase.onlineBookingDao().updateOnlineBookingStatusById(
                                id = bookingId,
                                status = 3
                            )
                        }//end if
                        else if (status.toData()?.statusCode == 403) {
                            localDatabase.onlineBookingDao().deleteOnlineBookingById(
                                id = bookingId
                            )
                        }//end else if
                        trySend(status)
                    }//end success status

                    is Status.Error -> {
                        trySend(status)
                    }//end error status

                    is Status.Loading -> {
                        trySend(status)
                    }//end loading status

                }//end when

            }//end infiniteWrapper

        }.flowOn(Dispatchers.IO)//end channelFlow

    }//end getPaymentIntent

}//end OfflineBookingRepositoryImpl