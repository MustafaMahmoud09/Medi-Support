package com.example.bmi.data.repository.execution.cacheHelperExecution

import android.util.Log
import com.example.blood.sugar.domain.mapper.declarations.child.IBMIDtoToBMIEntityMapper
import com.example.bmi.data.repository.execution.cacheHelperDeclarations.ICacheBMIRepositoryHelper
import com.example.bmi.data.source.local.data.entity.execution.bmi.BMIEntity
import com.example.bmi.data.source.remote.data.dto.execution.BMIDto
import com.example.bmi.domain.dto.declarations.pageRecords.IPageBMIResponseDto
import com.example.database_creator.MediSupportDatabase

class CacheBMIRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val bmiDtoToBMIEntityMapper: IBMIDtoToBMIEntityMapper
) : ICacheBMIRepositoryHelper {

    //function for cache latest bmi records in local database
    override suspend fun cacheLatestBMIRecords(
        bmiRecords: List<BMIDto>,
        userId: Long
    ) {

        if (bmiRecords.isNotEmpty()) {

            //update user id to user id in local database
            val latestHeartRateDto = bmiRecords.map { bloodSugarRecord ->
                bloodSugarRecord.copy(
                    userId = userId
                )
            }//end map

            //convert latest blood pressure dto to blood pressure entity
            val heartRateEntities = bmiDtoToBMIEntityMapper.listConvertor(
                list = latestHeartRateDto
            ) as List<BMIEntity>


            for (count in 0 until heartRateEntities.size - 1) {

                //execute delete here for extra data
                localDatabase.bmiDao().deleteBMIFromIdToId(
                    startId = heartRateEntities[count + 1].id,
                    endId = heartRateEntities[count].id,
                    userId = userId
                )

            }//for loop

            //execute delete here for extra data
            localDatabase.bmiDao().deleteBMIRecordsFromId(
                startId = heartRateEntities[0].id,
                userId = userId
            )

            //cache data in local here
            localDatabase.bmiDao().insertBMIRecord(
                bmiRecords = heartRateEntities
            )

        }//end if
        else {

            //execute delete all caching data here
            localDatabase.bmiDao()
                .deleteBMIRecordsFromId(
                    userId = userId,
                    startId = 0
                )
        }//end else

    }//end cacheLatestBMIRecords


    //function for cache page contain on blood sugar records in local database
    override suspend fun cachePageBMIRecords(
        records: IPageBMIResponseDto?,
        pageSize: Int,
        userId: Long
    ): Int {

        try {

            if (records?.data!!.data!!.isNotEmpty()) {

                //update user id to user id in local database
                val bmiRecordsDto =
                    ((records.data?.data!!) as List<BMIDto>).map { bmiRecord ->
                        bmiRecord.copy(
                            userId = userId
                        )
                    }//end map

                //execute map data from dto to entity here
                val bmiEntities =
                    bmiDtoToBMIEntityMapper.listConvertor(
                        list = bmiRecordsDto
                    )

                //store article entities in local database here
                localDatabase.bmiDao().insertBMIRecord(
                    bmiRecords = bmiEntities as List<BMIEntity>
                )

                //if current page is first page
                //delete first items if caching from 0 to first item exist in server now
                if (records.data?.links?.currentPage == 1) {

                    //execute delete here
                    localDatabase.bmiDao().deleteBMIFromIdToId(
                        startId = 0L,
                        endId = bmiEntities[0].id,
                        userId = userId
                    )

                }//end if
                else {

                    val prevBMI = localDatabase.bmiDao().selectPageBMI(
                        page = records.data?.links?.currentPage!! - 1,
                        pageSize = pageSize,
                        userId = userId
                    )

                    //execute delete here
                    localDatabase.bmiDao().deleteBMIFromIdToId(
                        startId = prevBMI[prevBMI.size - 1].id,
                        endId = bmiEntities[0].id,
                        userId = userId
                    )

                }//end else

                //execute delete between items here
                for (count in 0 until bmiEntities.size - 1) {

                    //execute delete here
                    localDatabase.bmiDao().deleteBMIFromIdToId(
                        startId = bmiEntities[count].id,
                        endId = bmiEntities[count + 1].id,
                        userId = userId
                    )

                }//end for

                //if current page is last page
                //delete items from last item in server to last item exist in local if exist
                if (records.data?.links?.currentPage == records.data?.links?.lastPage) {

                    //execute delete here
                    localDatabase.bmiDao().deleteBMIRecordsFromId(
                        startId = bmiEntities[bmiEntities.size - 1].id,
                        userId = userId
                    )

                }//end if

            }//end if
            else {

                //if current page is first page
                if (records.data?.links?.currentPage == 1) {

                    //execute delete here
                    localDatabase.bmiDao().deleteBMIRecordsFromId(
                        startId = 0,
                        userId = userId
                    )

                }//end if
                else {

                    val prevBMI = localDatabase.bmiDao().selectPageBMI(
                        page = records.data?.links?.currentPage!! - 1,
                        pageSize = pageSize,
                        userId = userId
                    )

                    localDatabase.bmiDao().deleteBMIRecordsFromId(
                        startId = prevBMI[prevBMI.size - 1].id,
                        userId = userId
                    )

                }//end else

            }//end else

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("TAG", it) }
        }//end catch

        return records?.data?.links?.lastPage ?: 0

    }//end cachePageBloodSugarRecords


    override suspend fun getLocalPageCount(
        pageSize: Int,
        userId: Long
    ): Int {

        //get article size
        val bmiRecordsSize = localDatabase.bmiDao().selectBMICount(
            userId = userId
        )

        return if ((bmiRecordsSize.toFloat() / pageSize.toFloat()) - (bmiRecordsSize / pageSize) != 0f) {
            (bmiRecordsSize / pageSize) + 1
        }//end if
        else {
            (bmiRecordsSize / pageSize)
        }.toInt()//end else

    }//end getLocalPageCount

}//end HeartRateRepositoryHelper