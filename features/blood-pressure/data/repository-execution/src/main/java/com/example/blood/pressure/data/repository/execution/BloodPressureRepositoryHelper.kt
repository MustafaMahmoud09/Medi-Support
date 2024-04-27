package com.example.blood.pressure.data.repository.execution

import android.util.Log
import com.example.blood.pressure.data.source.local.data.entity.execution.bloodPressure.BloodPressureEntity
import com.example.blood.pressure.data.source.remote.data.dto.execution.BloodPressureDto
import com.example.blood.pressure.domain.dto.declarations.pageMeasurement.IPageBloodPressureResponseDto
import com.example.blood.pressure.domain.mapper.declarations.child.ILatestBloodPressureDtoToBloodPressureEntityMapper
import com.example.database_creator.MediSupportDatabase

class BloodPressureRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val latestBloodPressureDtoToBloodPressureEntityMapper: ILatestBloodPressureDtoToBloodPressureEntityMapper
) {

    //function for cache latest blood pressure record in local database
    suspend fun cacheLatestBloodPressureRecords(
        bloodPressureRecords: List<BloodPressureDto>,
        userId: Long
    ) {
        //update user id to user id in local database
        val latestBloodPressureDto = bloodPressureRecords.map { bloodPressureRecord ->
            bloodPressureRecord.copy(
                attributes = bloodPressureRecord.attributes?.copy(
                    userId = userId
                )
            )
        }

        //convert latest blood pressure dto to blood pressure entity
        val bloodPressureEntities = latestBloodPressureDtoToBloodPressureEntityMapper.listConvertor(
            list = latestBloodPressureDto
        ) as List<BloodPressureEntity>


        for (count in 0 until bloodPressureEntities.size - 1) {

            //execute delete here for extra data
            localDatabase.bloodPressureDao().deleteBloodPressuresFromIdToId(
                startId = bloodPressureEntities[count + 1].id,
                endId = bloodPressureEntities[count].id,
                userId = userId
            )

        }//for

        if (bloodPressureEntities.isNotEmpty()) {

            //execute delete here for extra data
            localDatabase.bloodPressureDao().deleteBloodPressureRecordsFromId(
                startId = bloodPressureEntities[0].id,
                userId = userId
            )

        }//end if

        //cache data in local here
        localDatabase.bloodPressureDao().insertBloodPressureRecord(
            bloodPressureRecords = bloodPressureEntities
        )

    }//end cacheLatestBloodPressureRecord


    //function for cache articles in local database
    suspend fun cacheBloodPressureRecords(
        records: IPageBloodPressureResponseDto?,
        userId: Long
    ): Int {

        try {

            if (records?.data!!.isNotEmpty()) {

                //update user id to user id in local database
                val bloodPressureRecordsDto = (records.data as List<BloodPressureDto>).map { bloodPressureRecord ->
                    bloodPressureRecord.copy(
                        attributes = bloodPressureRecord.attributes?.copy(
                            userId = userId
                        )
                    )
                }//end map

                //execute map data from dto to entity here
                val bloodPressureEntities =
                    latestBloodPressureDtoToBloodPressureEntityMapper.listConvertor(
                        list = bloodPressureRecordsDto
                    )

                //store article entities in local database here
                localDatabase.bloodPressureDao().insertBloodPressureRecord(
                    bloodPressureRecords = bloodPressureEntities as List<BloodPressureEntity>
                )

                //if current page is first page
                //delete first items if caching from 0 to first item exist in server now
                if (records.meta?.currentPage == 1) {

                    //execute delete here
                    localDatabase.bloodPressureDao().deleteBloodPressuresFromIdToId(
                        startId = 0L,
                        endId = bloodPressureEntities[0].id,
                        userId = userId
                    )

                }//end if
                else {

                    val prevArticles = localDatabase.bloodPressureDao().selectPageBloodPressure(
                        page = records.meta?.currentPage!! - 1,
                        pageSize = 10,
                        userId = userId
                    )

                    //execute delete here
                    localDatabase.bloodPressureDao().deleteBloodPressuresFromIdToId(
                        startId = prevArticles[prevArticles.size - 1].id,
                        endId = bloodPressureEntities[0].id,
                        userId = userId
                    )

                }//end else

                //execute delete between items here
                for (count in 0 until bloodPressureEntities.size - 1) {

                    //execute delete here
                    localDatabase.bloodPressureDao().deleteBloodPressuresFromIdToId(
                        startId = bloodPressureEntities[count].id,
                        endId = bloodPressureEntities[count + 1].id,
                        userId = userId
                    )

                }//end for

                //if current page is last page
                //delete items from last item in server to last item exist in local if exist
                if (records.meta?.currentPage == records.meta?.lastPage) {

                    //execute delete here
                    localDatabase.bloodPressureDao().deleteBloodPressureRecordsFromId(
                        startId = bloodPressureEntities[bloodPressureEntities.size - 1].id,
                        userId = userId
                    )

                }//end if

            }//end if
            else {

                //if current page is first page
                if (records.meta?.currentPage == 1) {

                    //execute delete here
                    localDatabase.bloodPressureDao().deleteBloodPressureRecordsFromId(
                        startId = 0,
                        userId = userId
                    )

                }//end if
                else {

                    val prevArticles = localDatabase.bloodPressureDao().selectPageBloodPressure(
                        page = records.meta?.currentPage!! - 1,
                        pageSize = 10,
                        userId = userId
                    )

                    localDatabase.bloodPressureDao().deleteBloodPressureRecordsFromId(
                        startId = prevArticles[prevArticles.size - 1].id,
                        userId = userId
                    )

                }//end else

            }//end else

        }//end try
        catch (ex: Exception) {
            ex.message?.let { Log.d("TAG", it) }
        }//end catch

        return records?.meta?.lastPage!!

    }//end cacheArticles

}//end BloodPressureRepositoryHelper