package com.example.heart.rate.data.source.data.access.`object`

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.heart.rate.data.source.entity.execution.heartRate.HeartRateInfo
import com.example.heart.rate.data.source.entity.execution.heartRate.HeartRateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeartRateDao {

    //TODO:: FUNCTION FOR INSERT HEART RATE RECORDS IN DATABASE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeartRateRecord(
        bloodPressureRecords: List<HeartRateEntity>
    )


    //TODO:: FUNCTION FOR GET LATEST HEART RATE RECORDS FROM DATABASE
    @Query(
        "SELECT * FROM ${
            HeartRateInfo.HEART_RATE_TABLE_NAME
        } WHERE ${
            HeartRateInfo.USER_ID_COLUMN_NAME
        } = :userId ORDER BY ${
            HeartRateInfo.ID_COLUMN_NAME
        } DESC " +
                "LIMIT :limit"
    )
    fun getLatestHeartRateRecord(
        userId: Long,
        limit: Long,
    ): Flow<List<HeartRateEntity>>


    //TODO:: FUNCTION FOR DELETE HEART RATE RECORDS FROM DATABASE FROM START ID
    @Query(
        "DELETE FROM ${
            HeartRateInfo.HEART_RATE_TABLE_NAME
        } WHERE ${
            HeartRateInfo.USER_ID_COLUMN_NAME
        } = :userId AND ${
            HeartRateInfo.ID_COLUMN_NAME
        } > :startId"
    )
    suspend fun deleteHeartRateRecordsFromId(
        startId: Long,
        userId: Long
    )


    //TODO:: FUNCTION FOR SELECT PAGE FROM HEART RATE TABLE
    @Transaction
    @Query(
        "SELECT * FROM ${
            HeartRateInfo.HEART_RATE_TABLE_NAME
        } WHERE ${
            HeartRateInfo.USER_ID_COLUMN_NAME
        } == :userId ORDER BY ${
            HeartRateInfo.ID_COLUMN_NAME
        } DESC LIMIT :pageSize" +
                " OFFSET ((:page - 1) * :pageSize)"
    )
    suspend fun selectPageHeartRate(
        pageSize: Int,
        page: Int,
        userId: Long
    ): List<HeartRateEntity>


    //TODO:: FUNCTION FOR PROVIDE RECORD COUNT
    @Query(
        "SELECT COUNT(*) FROM ${
            HeartRateInfo.HEART_RATE_TABLE_NAME
        }"
    )
    suspend fun selectHeartRateCount(): Long


    //TODO:: FUNCTION FROM DELETE HEART RATE FROM START ID TO END ID
    @Query(
        "DELETE FROM ${
            HeartRateInfo.HEART_RATE_TABLE_NAME
        } WHERE( ${
            HeartRateInfo.ID_COLUMN_NAME
        } > :startId AND ${
            HeartRateInfo.ID_COLUMN_NAME
        } < :endId) AND ${
            HeartRateInfo.USER_ID_COLUMN_NAME
        } == :userId"
    )
    suspend fun deleteHeartRateFromIdToId(startId: Long, endId: Long, userId: Long)

}//end HeartRateDao