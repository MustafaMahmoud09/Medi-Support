package com.example.blood.pressure.data.repository.execution

import com.example.blood.pressure.data.source.local.data.entity.execution.bloodPressure.BloodPressureEntity
import com.example.blood.pressure.data.source.remote.data.dto.execution.latestMeasurement.LatestBloodPressureDto
import com.example.blood.pressure.domain.dto.declarations.latestMeasurement.ILatestBloodPressureDto
import com.example.blood.pressure.domain.mapper.declarations.child.ILatestBloodPressureDtoToBloodPressureEntityMapper
import com.example.database_creator.MediSupportDatabase

class BloodPressureRepositoryHelper(
    private val localDatabase: MediSupportDatabase,
    private val latestBloodPressureDtoToBloodPressureEntityMapper: ILatestBloodPressureDtoToBloodPressureEntityMapper
) {

    //function for cache latest blood pressure record in local database
    suspend fun cacheLatestBloodPressureRecord(
        bloodPressureRecord: LatestBloodPressureDto,
        userId: Long
    ) {
        //update user id to user id in local database
        val latestBloodPressureDto = bloodPressureRecord.copy(
            attributes = bloodPressureRecord.attributes?.copy(
                userId = userId
            )
        )

        //convert latest blood pressure dto to blood pressure entity
        val bloodPressureEntities = latestBloodPressureDtoToBloodPressureEntityMapper.listConvertor(
            list = listOf(latestBloodPressureDto)
        ) as List<BloodPressureEntity>

        //cache data in local here
        localDatabase.bloodPressureDao().insertBloodPressureRecord(
            bloodPressureRecords = bloodPressureEntities
        )

    }//end cacheLatestBloodPressureRecord



}//end BloodPressureRepositoryHelper