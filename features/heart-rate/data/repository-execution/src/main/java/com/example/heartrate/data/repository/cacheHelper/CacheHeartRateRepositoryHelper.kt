package com.example.heartrate.data.repository

import android.util.Log
import com.example.database_creator.MediSupportDatabase
import com.example.heart.rate.data.source.dto.execution.HeartRateDto
import com.example.heart.rate.data.source.dto.execution.pageRecords.IPageHeartRateResponseDto

class HeartRateRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
private val bloodSugarDtoToBloodSugarEntityMapper: IBloodSugarDtoToBloodSugarEntityMapper
) {

    //function for cache latest blood sugar records in local database
    suspend fun cacheLatestBloodSugarRecords(
        bloodSugarRecords: List<HeartRateDto>,
        userId: Long
    ) {

        //update user id to user id in local database
        val latestBloodSugarDto = bloodSugarRecords.map { bloodSugarRecord ->
            bloodSugarRecord.copy(
                userId = userId
            )
        }

        //convert latest blood pressure dto to blood pressure entity
        val bloodSugarEntities = bloodSugarDtoToBloodSugarEntityMapper.listConvertor(
            list = latestBloodSugarDto
        ) as List<BloodSugarEntity>


        for (count in 0 until bloodSugarEntities.size - 1) {

            //execute delete here for extra data
            localDatabase.bloodSugarDao().deleteBloodSugarsFromIdToId(
                startId = bloodSugarEntities[count + 1].id,
                endId = bloodSugarEntities[count].id,
                userId = userId
            )

        }//for loop

        if (bloodSugarEntities.isNotEmpty()) {

            //execute delete here for extra data
            localDatabase.bloodSugarDao().deleteBloodSugarRecordsFromId(
                startId = bloodSugarEntities[0].id,
                userId = userId
            )

        }//end if

        //cache data in local here
        localDatabase.bloodSugarDao().insertBloodSugarRecord(
            bloodPressureRecords = bloodSugarEntities
        )

    }//end cacheLatestBloodSugarRecords


    //function for cache page contain on blood sugar records in local database
    suspend fun cachePageBloodSugarRecords(
        records: IPageHeartRateResponseDto?,
        userId: Long
    ): Int {

        try {

            if (records?.data!!.records!!.isNotEmpty()) {

                //update user id to user id in local database
                val bloodSugarRecordsDto =
                    ((records.data?.records!!) as List<BloodSugarDto>).map { bloodPressureRecord ->
                        bloodPressureRecord.copy(
                            userId = userId
                        )
                    }//end map

                //execute map data from dto to entity here
                val bloodSugarEntities =
                    bloodSugarDtoToBloodSugarEntityMapper.listConvertor(
                        list = bloodSugarRecordsDto
                    )

                //store article entities in local database here
                localDatabase.bloodSugarDao().insertBloodSugarRecord(
                    bloodPressureRecords = bloodSugarEntities as List<BloodSugarEntity>
                )

                //if current page is first page
                //delete first items if caching from 0 to first item exist in server now
                if (records.data?.currentPage == 1) {

                    //execute delete here
                    localDatabase.bloodSugarDao().deleteBloodSugarRecordsFromId(
                        startId = bloodSugarEntities[0].id,
                        userId = userId
                    )

                }//end if
                else {

                    val prevBloodSugar = localDatabase.bloodSugarDao().selectPageBloodSugar(
                        page = records.data?.currentPage!! - 1,
                        pageSize = 10,
                        userId = userId
                    )

                    //execute delete here
                    localDatabase.bloodSugarDao().deleteBloodSugarsFromIdToId(
                        startId = bloodSugarEntities[0].id,
                        endId = prevBloodSugar[prevBloodSugar.size - 1].id,
                        userId = userId
                    )

                }//end else

                //execute delete between items here
                for (count in 0 until bloodSugarEntities.size - 1) {

                    //execute delete here for extra data
                    localDatabase.bloodSugarDao().deleteBloodSugarsFromIdToId(
                        startId = bloodSugarEntities[count + 1].id,
                        endId = bloodSugarEntities[count].id,
                        userId = userId
                    )

                }//end for

                //if current page is last page
                //delete items from last item in server to last item exist in local if exist
                if (records.data?.currentPage == records.data?.lastPage) {

                    //execute delete here
                    localDatabase.bloodSugarDao().deleteBloodSugarsFromIdToId(
                        startId = 0,
                        endId = bloodSugarEntities[bloodSugarEntities.size - 1].id,
                        userId = userId
                    )

                }//end if

            }//end if
            else {

                //if current page is first page
                if (records.data?.currentPage == 1) {

                    //execute delete here
                    localDatabase.bloodSugarDao().deleteBloodSugarRecordsFromId(
                        startId = 0,
                        userId = userId
                    )

                }//end if
                else {

                    val prevBloodSugars = localDatabase.bloodSugarDao().selectPageBloodSugar(
                        page = records.data?.currentPage!! - 1,
                        pageSize = 10,
                        userId = userId
                    )

                    localDatabase.bloodSugarDao().deleteBloodSugarsFromIdToId(
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

}//end HeartRateRepositoryHelper