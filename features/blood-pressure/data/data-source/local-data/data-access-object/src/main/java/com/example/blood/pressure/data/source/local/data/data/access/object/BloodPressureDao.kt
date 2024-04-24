package com.example.blood.pressure.data.source.local.data.data.access.`object`

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blood.pressure.data.source.local.data.entity.execution.bloodPressure.BloodPressureEntity
import com.example.blood.pressure.data.source.local.data.entity.execution.bloodPressure.BloodPressureInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodPressureDao {

    //TODO:: FUNCTION FOR INSERT BLOOD PRESSURE RECORD IN DATABASE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBloodPressureRecord(
        bloodPressureRecords: List<BloodPressureEntity>
    )


    //TODO:: FUNCTION FOR GET LATEST BLOOD PRESSURE RECORD FROM DATABASE
    @Query(
        "SELECT * FROM ${
            BloodPressureInfo.BLOOD_PRESSURE_TABLE_NAME
        } WHERE ${
            BloodPressureInfo.USER_ID_COLUMN_NAME
        } = :userId ORDER BY ${
            BloodPressureInfo.ID_COLUMN_NAME
        } DESC " +
                "LIMIT 1"
    )
    fun getLatestBloodPressureRecord(
        userId: Long
    ): Flow<List<BloodPressureEntity>>


    //TODO:: FUNCTION FOR DELETE BLOOD PRESSURE RECORDS FROM DATABASE FROM START ID
    @Query(
        "DELETE FROM ${
            BloodPressureInfo.BLOOD_PRESSURE_TABLE_NAME
        } WHERE ${
            BloodPressureInfo.USER_ID_COLUMN_NAME
        } = :userId AND ${
            BloodPressureInfo.ID_COLUMN_NAME
        } > :startId"
    )
    suspend fun deleteBloodPressureRecordsFromId(
        startId: Long,
        userId: Long
    )

}//end BloodPressureDao