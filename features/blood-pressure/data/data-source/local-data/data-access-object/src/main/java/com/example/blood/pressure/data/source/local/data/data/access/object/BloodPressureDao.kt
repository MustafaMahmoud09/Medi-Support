package com.example.blood.pressure.data.source.local.data.data.access.`object`

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
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
                "LIMIT :limit"
    )
    fun getLatestBloodPressureRecord(
        userId: Long,
        limit: Long,
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


    //TODO:: FUNCTION FOR SELECT PAGE FROM BLOOD PRESSURE TABLE
    @Transaction
    @Query(
        "SELECT * FROM ${
            BloodPressureInfo.BLOOD_PRESSURE_TABLE_NAME
        } WHERE ${
            BloodPressureInfo.USER_ID_COLUMN_NAME
        } == :userId ORDER BY ${
            BloodPressureInfo.ID_COLUMN_NAME
        } ASC LIMIT :pageSize" +
                " OFFSET ((:page - 1) * :pageSize)"
    )
    suspend fun selectPageBloodPressure(
        pageSize: Int,
        page: Int,
        userId: Long
    ): List<BloodPressureEntity>


    //TODO:: FUNCTION FOR PROVIDE BLOOD PRESSURE RECORD COUNT
    @Query(
        "SELECT COUNT(*) FROM ${
            BloodPressureInfo.BLOOD_PRESSURE_TABLE_NAME
        }"
    )
    suspend fun selectBloodPressureCount(): Long


    //TODO:: FUNCTION FROM DELETE ARTICLES FROM START ID TO END ID
    @Query(
        "DELETE FROM ${
            BloodPressureInfo.BLOOD_PRESSURE_TABLE_NAME
        } WHERE( ${
            BloodPressureInfo.ID_COLUMN_NAME
        } > :startId AND ${
            BloodPressureInfo.ID_COLUMN_NAME
        } < :endId) AND ${
            BloodPressureInfo.USER_ID_COLUMN_NAME
        } == :userId"
    )
    suspend fun deleteBloodPressuresFromIdToId(startId: Long, endId: Long, userId: Long)

}//end BloodPressureDao