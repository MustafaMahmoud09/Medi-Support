package com.example.bmi.data.source.local.data.data.access.`object`

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.bmi.data.source.local.data.entity.execution.bmi.BMIEntity
import com.example.bmi.data.source.local.data.entity.execution.bmi.BMIInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BMIDao {

    //TODO:: FUNCTION FOR INSERT BMI RECORDS IN DATABASE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBMIRecord(
        bmiRecords: List<BMIEntity>
    )


    //TODO:: FUNCTION FOR GET LATEST BMI RECORDS FROM DATABASE
    @Query(
        "SELECT * FROM ${
            BMIInfo.BMI_TABLE_NAME
        } WHERE ${
            BMIInfo.USER_ID_COLUMN_NAME
        } = :userId ORDER BY ${
            BMIInfo.ID_COLUMN_NAME
        } DESC " +
                "LIMIT :limit"
    )
    fun getLatestBMIRecord(
        userId: Long,
        limit: Long,
    ): Flow<List<BMIEntity>>


    //TODO:: FUNCTION FOR DELETE BMI RECORDS FROM DATABASE FROM START ID
    @Query(
        "DELETE FROM ${
            BMIInfo.BMI_TABLE_NAME
        } WHERE ${
            BMIInfo.USER_ID_COLUMN_NAME
        } = :userId AND ${
            BMIInfo.ID_COLUMN_NAME
        } > :startId"
    )
    suspend fun deleteBMIRecordsFromId(
        startId: Long,
        userId: Long
    )


    //TODO:: FUNCTION FOR SELECT PAGE FROM BMI TABLE
    @Transaction
    @Query(
        "SELECT * FROM ${
            BMIInfo.BMI_TABLE_NAME
        } WHERE ${
            BMIInfo.USER_ID_COLUMN_NAME
        } == :userId ORDER BY ${
            BMIInfo.ID_COLUMN_NAME
        } ASC LIMIT :pageSize" +
                " OFFSET ((:page - 1) * :pageSize)"
    )
    suspend fun selectPageBMI(
        pageSize: Int,
        page: Int,
        userId: Long
    ): List<BMIEntity>


    //TODO:: FUNCTION FOR PROVIDE BMI RECORD COUNT
    @Query(
        "SELECT COUNT(*) FROM ${
            BMIInfo.BMI_TABLE_NAME
        } WHERE ${
            BMIInfo.USER_ID_COLUMN_NAME
        } = :userId"
    )
    suspend fun selectBMICount(
        userId: Long
    ): Long


    //TODO:: FUNCTION FROM DELETE BMI FROM START ID TO END ID
    @Query(
        "DELETE FROM ${
            BMIInfo.BMI_TABLE_NAME
        } WHERE( ${
            BMIInfo.ID_COLUMN_NAME
        } > :startId AND ${
            BMIInfo.ID_COLUMN_NAME
        } < :endId) AND ${
            BMIInfo.USER_ID_COLUMN_NAME
        } == :userId"
    )
    suspend fun deleteBMIFromIdToId(startId: Long, endId: Long, userId: Long)

}//end BMIDao