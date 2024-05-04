package com.example.blood.sugar.data.repository.execution.cacheHelperExecution

import android.util.Log
import com.example.offline.booking.data.repository.execution.cacheHelperDeclarations.ICacheOfflineBookingRepositoryHelper
import com.example.database_creator.MediSupportDatabase
import com.example.offline.booking.data.source.local.data.entity.execution.offlineBooking.OfflineBookingEntity
import com.example.offline.booking.data.source.remote.data.dto.execution.bookingDetails.OfflineBookingDto
import com.example.offline.booking.domain.dto.declarations.bookingDetails.IBookingDetailResponseDto
import com.example.offline.booking.domain.mapper.declarations.child.IOfflineBookingDtoToOfflineBookingEntityMapper

class CacheOnlineBookingRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val offlineDtoToOfflineBookingEntityMapper: IOfflineBookingDtoToOfflineBookingEntityMapper
) : ICacheOfflineBookingRepositoryHelper {


    //function for cache page contain on blood sugar records in local database
    override suspend fun cachePageOfflineBookingRecords(
        records: IBookingDetailResponseDto,
        pageSize: Int,
        userId: Long
    ): Int{

        try {

            if (records.data?.data!!.isNotEmpty()) {

                //update user id to user id in local database
                val offlineBookingRecordsDto =
                    ((records.data?.data!!) as List<OfflineBookingDto>).map { bloodPressureRecord ->
                        bloodPressureRecord.copy(
                            userId = userId
                        )
                    }//end map

                //execute map data from dto to entity here
                val offlineBookingEntities =
                    offlineDtoToOfflineBookingEntityMapper.listConvertor(
                        list = offlineBookingRecordsDto
                    )

                //store article entities in local database here
                localDatabase.offlineBookingDao().insertOfflineBookingRecord(
                    bloodPressureRecords = offlineBookingEntities as List<OfflineBookingEntity>
                )

                //if current page is first page
                //delete first items if caching from 0 to first item exist in server now
                if (records.data?.currentPage == 1) {

                    //execute delete here
                    localDatabase.offlineBookingDao().deleteOfflineBookingRecordsFromId(
                        startId = offlineBookingEntities[0].id,
                        userId = userId
                    )

                }//end if
                else {

                    val prevOfflineBookings = localDatabase.offlineBookingDao().selectPageOfflineBooking(
                        page = records.data?.currentPage!! - 1,
                        pageSize = pageSize,
                        userId = userId
                    )

                    //execute delete here
                    localDatabase.offlineBookingDao().deleteOfflineBookingFromIdToId(
                        startId = offlineBookingEntities[0].id,
                        endId = prevOfflineBookings[prevOfflineBookings.size - 1].id,
                        userId = userId
                    )

                }//end else

                //execute delete between items here
                for (count in 0 until offlineBookingEntities.size - 1) {

                    //execute delete here for extra data
                    localDatabase.offlineBookingDao().deleteOfflineBookingFromIdToId(
                        startId = offlineBookingEntities[count + 1].id,
                        endId = offlineBookingEntities[count].id,
                        userId = userId
                    )

                }//end for

                //if current page is last page
                //delete items from last item in server to last item exist in local if exist
                if (records.data?.currentPage == records.data?.lastPage) {

                    //execute delete here
                    localDatabase.offlineBookingDao().deleteOfflineBookingFromIdToId(
                        startId = 0,
                        endId = offlineBookingEntities[offlineBookingEntities.size - 1].id,
                        userId = userId
                    )

                }//end if

            }//end if
            else {

                //if current page is first page
                if (records.data?.currentPage == 1) {

                    //execute delete here
                    localDatabase.offlineBookingDao().deleteOfflineBookingRecordsFromId(
                        startId = 0,
                        userId = userId
                    )

                }//end if
                else {

                    val prevOfflineBooking = localDatabase.offlineBookingDao().selectPageOfflineBooking(
                        page = records.data?.currentPage!! - 1,
                        pageSize = pageSize,
                        userId = userId
                    )

                    localDatabase.offlineBookingDao().deleteOfflineBookingFromIdToId(
                        startId = 0,
                        endId = prevOfflineBooking[prevOfflineBooking.size - 1].id,
                        userId = userId
                    )

                }//end else

            }//end else

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("TAG", it) }
        }//end catch

        return records.data?.lastPage ?: 0

    }//end cachePageBloodSugarRecords


    override suspend fun getLocalPageCount(
        pageSize: Int,
        userId: Long
    ): Int {

        //get article size
        val offlineBookingRecords = localDatabase.offlineBookingDao().selectOfflineBookingCount(
            userId = userId
        )

        return if ((offlineBookingRecords.toFloat() / pageSize.toFloat()) - (offlineBookingRecords / pageSize) != 0f) {
            (offlineBookingRecords / pageSize) + 1
        }//end if
        else {
            (offlineBookingRecords / pageSize)
        }.toInt()//end else

    }//end getLocalPageCount

}//end CacheOnlineBookingRepositoryHelper

