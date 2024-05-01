package com.example.heartrate.data.repository.cacheHelperExecution

import android.util.Log
import com.example.blood.sugar.domain.mapper.declarations.child.IHeartRateDtoToHeartRateEntityMapper
import com.example.database_creator.MediSupportDatabase
import com.example.heart.rate.data.source.dto.execution.HeartRateDto
import com.example.heart.rate.data.source.dto.execution.pageRecords.IPageHeartRateResponseDto
import com.example.heart.rate.data.source.entity.execution.heartRate.HeartRateEntity
import com.example.heartrate.data.repository.cacheHelperDeclarations.ICacheHeartRateRepositoryHelper

class CacheHeartRateRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val heartRateDtoToHeartRateEntityMapper: IHeartRateDtoToHeartRateEntityMapper
): ICacheHeartRateRepositoryHelper {

    //function for cache latest heart rate records in local database
    override suspend fun cacheLatestHeartRateRecords(
        heartRateRecords: List<HeartRateDto>,
        userId: Long
    ) {

        if (heartRateRecords.isNotEmpty()) {

            //update user id to user id in local database
            val latestHeartRateDto = heartRateRecords.map { bloodSugarRecord ->
                bloodSugarRecord.copy(
                    userId = userId
                )
            }//end map

            //convert latest blood pressure dto to blood pressure entity
            val heartRateEntities = heartRateDtoToHeartRateEntityMapper.listConvertor(
                list = latestHeartRateDto
            ) as List<HeartRateEntity>


            for (count in 0 until heartRateEntities.size - 1) {

                //execute delete here for extra data
                localDatabase.heartRateDao().deleteHeartRateFromIdToId(
                    startId = heartRateEntities[count + 1].id,
                    endId = heartRateEntities[count].id,
                    userId = userId
                )

            }//for loop

            if (heartRateEntities.isNotEmpty()) {

                //execute delete here for extra data
                localDatabase.heartRateDao().deleteHeartRateRecordsFromId(
                    startId = heartRateEntities[0].id,
                    userId = userId
                )

            }//end if

            //cache data in local here
            localDatabase.heartRateDao().insertHeartRateRecord(
                bloodPressureRecords = heartRateEntities
            )

        }//end if
        else {

            //execute delete all caching data here
            localDatabase.heartRateDao()
                .deleteHeartRateRecordsFromId(
                    userId = userId,
                    startId = 0
                )
        }//end else

    }//end cacheLatestHeartRateRecords


    //function for cache page contain on blood sugar records in local database
    override suspend fun cachePageHeartRateRecords(
        records: IPageHeartRateResponseDto?,
        pageSize: Int,
        userId: Long
    ): Int {

        try {

            if (records?.data!!.records!!.isNotEmpty()) {

                //update user id to user id in local database
                val heartRateRecordsDto =
                    ((records.data?.records!!) as List<HeartRateDto>).map { bloodPressureRecord ->
                        bloodPressureRecord.copy(
                            userId = userId
                        )
                    }//end map

                //execute map data from dto to entity here
                val heartRateEntities =
                    heartRateDtoToHeartRateEntityMapper.listConvertor(
                        list = heartRateRecordsDto
                    )

                //store article entities in local database here
                localDatabase.heartRateDao().insertHeartRateRecord(
                    bloodPressureRecords = heartRateEntities as List<HeartRateEntity>
                )

                //if current page is first page
                //delete first items if caching from 0 to first item exist in server now
                if (records.data?.currentPage == 1) {

                    //execute delete here
                    localDatabase.heartRateDao().deleteHeartRateRecordsFromId(
                        startId = heartRateEntities[0].id,
                        userId = userId
                    )

                }//end if
                else {

                    val prevBloodSugar = localDatabase.heartRateDao().selectPageHeartRate(
                        page = records.data?.currentPage!! - 1,
                        pageSize = pageSize,
                        userId = userId
                    )

                    //execute delete here
                    localDatabase.heartRateDao().deleteHeartRateFromIdToId(
                        startId = heartRateEntities[0].id,
                        endId = prevBloodSugar[prevBloodSugar.size - 1].id,
                        userId = userId
                    )

                }//end else

                //execute delete between items here
                for (count in 0 until heartRateEntities.size - 1) {

                    //execute delete here for extra data
                    localDatabase.heartRateDao().deleteHeartRateFromIdToId(
                        startId = heartRateEntities[count + 1].id,
                        endId = heartRateEntities[count].id,
                        userId = userId
                    )

                }//end for

                //if current page is last page
                //delete items from last item in server to last item exist in local if exist
                if (records.data?.currentPage == records.data?.lastPage) {

                    //execute delete here
                    localDatabase.heartRateDao().deleteHeartRateFromIdToId(
                        startId = 0,
                        endId = heartRateEntities[heartRateEntities.size - 1].id,
                        userId = userId
                    )

                }//end if

            }//end if
            else {

                //if current page is first page
                if (records.data?.currentPage == 1) {

                    //execute delete here
                    localDatabase.heartRateDao().deleteHeartRateRecordsFromId(
                        startId = 0,
                        userId = userId
                    )

                }//end if
                else {

                    val prevBloodSugars = localDatabase.heartRateDao().selectPageHeartRate(
                        page = records.data?.currentPage!! - 1,
                        pageSize = pageSize,
                        userId = userId
                    )

                    localDatabase.heartRateDao().deleteHeartRateFromIdToId(
                        startId = 0,
                        endId = prevBloodSugars[prevBloodSugars.size - 1].id,
                        userId = userId
                    )

                }//end else

            }//end else

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("TAG", it) }
        }//end catch

        return records?.data?.lastPage ?: 0

    }//end cachePageBloodSugarRecords


    override suspend fun getLocalPageCount(
        pageSize: Int
    ): Int {

        //get article size
        val heartRateRecordsSize = localDatabase.heartRateDao().selectHeartRateCount()

        return if ((heartRateRecordsSize.toFloat() / pageSize.toFloat()) - (heartRateRecordsSize / pageSize) != 0f) {
            (heartRateRecordsSize / pageSize) + 1
        }//end if
        else {
            (heartRateRecordsSize / pageSize)
        }.toInt()//end else

    }//end getLocalPageCount

}//end HeartRateRepositoryHelper