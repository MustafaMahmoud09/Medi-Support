package com.example.blood.sugar.data.repository.execution.cacheHelperExecution

import android.util.Log
import com.example.online.booking.data.repository.execution.cacheHelperDeclarations.ICacheOnlineBookingRepositoryHelper
import com.example.database_creator.MediSupportDatabase
import com.example.online.booking.data.source.local.data.entity.execution.onlineBooking.OnlineBookingEntity
import com.example.online.booking.data.source.remote.data.dto.execution.bookingDetails.BookingDetailDto
import com.example.online.booking.domain.dto.declarations.bookingDetails.IBookingDetailsResponseDto
import com.example.online.booking.domain.mapper.declarations.child.IOnlineBookingDtoToOnlineBookingEntityMapper

class CacheOnlineBookingRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val onlineBookingDtoToOnlineBookingEntityMapper: IOnlineBookingDtoToOnlineBookingEntityMapper
) : ICacheOnlineBookingRepositoryHelper {


    //function for cache page contain on blood sugar records in local database
    override suspend fun cachePageOnlineBookingRecords(
        records: IBookingDetailsResponseDto,
        pageSize: Int,
        userId: Long
    ): Int{

        try {

            if (records.data?.data!!.isNotEmpty()) {

                //update user id to user id in local database
                val onlineBookingRecordsDto =
                    ((records.data?.data!!) as List<BookingDetailDto>).map { bloodPressureRecord ->
                        bloodPressureRecord.copy(
                            userId = userId
                        )
                    }//end map

                //execute map data from dto to entity here
                val onlineBookingEntities =
                    onlineBookingDtoToOnlineBookingEntityMapper.listConvertor(
                        list = onlineBookingRecordsDto
                    )

                //store article entities in local database here
                localDatabase.onlineBookingDao().insertOnlineBookingRecord(
                    bloodPressureRecords = onlineBookingEntities as List<OnlineBookingEntity>
                )

                //if current page is first page
                //delete first items if caching from 0 to first item exist in server now
                if (records.data?.pagination?.currentPage == 1) {

                    //execute delete here
                    localDatabase.onlineBookingDao().deleteOnlineBookingRecordsFromId(
                        startId = onlineBookingEntities[0].id,
                        userId = userId
                    )

                }//end if
                else {

                    val prevOnlineBookings = localDatabase.onlineBookingDao().selectPageOnlineBooking(
                        page = records.data?.pagination?.currentPage!! - 1,
                        pageSize = pageSize,
                        userId = userId
                    )

                    //execute delete here
                    localDatabase.onlineBookingDao().deleteOnlineBookingFromIdToId(
                        startId = onlineBookingEntities[0].id,
                        endId = prevOnlineBookings[prevOnlineBookings.size - 1].id,
                        userId = userId
                    )

                }//end else

                //execute delete between items here
                for (count in 0 until onlineBookingEntities.size - 1) {

                    //execute delete here for extra data
                    localDatabase.onlineBookingDao().deleteOnlineBookingFromIdToId(
                        startId = onlineBookingEntities[count + 1].id,
                        endId = onlineBookingEntities[count].id,
                        userId = userId
                    )

                }//end for

                //if current page is last page
                //delete items from last item in server to last item exist in local if exist
                if (records.data?.pagination?.currentPage == records.data?.pagination?.lastPage) {

                    //execute delete here
                    localDatabase.onlineBookingDao().deleteOnlineBookingFromIdToId(
                        startId = 0,
                        endId = onlineBookingEntities[onlineBookingEntities.size - 1].id,
                        userId = userId
                    )

                }//end if

            }//end if
            else {

                //if current page is first page
                if (records.data?.pagination?.currentPage == 1) {

                    //execute delete here
                    localDatabase.onlineBookingDao().deleteOnlineBookingRecordsFromId(
                        startId = 0,
                        userId = userId
                    )

                }//end if
                else {

                    val prevOnlineBooking = localDatabase.onlineBookingDao().selectPageOnlineBooking(
                        page = records.data?.pagination?.currentPage!! - 1,
                        pageSize = pageSize,
                        userId = userId
                    )

                    localDatabase.onlineBookingDao().deleteOnlineBookingFromIdToId(
                        startId = 0,
                        endId = prevOnlineBooking[prevOnlineBooking.size - 1].id,
                        userId = userId
                    )

                }//end else

            }//end else

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("TAG", it) }
        }//end catch

        return records.data?.pagination?.lastPage ?: 0

    }//end cachePageBloodSugarRecords


    override suspend fun getLocalPageCount(
        pageSize: Int,
        userId: Long
    ): Int {

        //get article size
        val onlineBookingRecords = localDatabase.onlineBookingDao().selectOnlineBookingCount(
            userId = userId
        )

        return if ((onlineBookingRecords.toFloat() / pageSize.toFloat()) - (onlineBookingRecords / pageSize) != 0f) {
            (onlineBookingRecords / pageSize) + 1
        }//end if
        else {
            (onlineBookingRecords / pageSize)
        }.toInt()//end else

    }//end getLocalPageCount

}//end CacheOnlineBookingRepositoryHelper

