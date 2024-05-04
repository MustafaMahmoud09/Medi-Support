package com.example.blood.sugar.data.source.data.access.`object`

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.blood.sugar.data.source.local.data.entity.execution.bloodSugar.BloodSugarEntity
import com.example.blood.sugar.data.source.local.data.entity.execution.bloodSugar.BloodSugarInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodSugarDao {

    //TODO:: FUNCTION FOR INSERT BLOOD SUGAR RECORDS IN DATABASE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBloodSugarRecord(
        bloodPressureRecords: List<BloodSugarEntity>
    )


    //TODO:: FUNCTION FOR GET LATEST BLOOD SUGAR RECORDS FROM DATABASE
    @Query(
        "SELECT * FROM ${
            BloodSugarInfo.BLOOD_SUGAR_TABLE_NAME
        } WHERE ${
            BloodSugarInfo.USER_ID_COLUMN_NAME
        } = :userId ORDER BY ${
            BloodSugarInfo.ID_COLUMN_NAME
        } DESC " +
                "LIMIT :limit"
    )
    fun getLatestBloodSugarRecord(
        userId: Long,
        limit: Long,
    ): Flow<List<BloodSugarEntity>>


    //TODO:: FUNCTION FOR DELETE BLOOD SUGAR RECORDS FROM DATABASE FROM START ID
    @Query(
        "DELETE FROM ${
            BloodSugarInfo.BLOOD_SUGAR_TABLE_NAME
        } WHERE ${
            BloodSugarInfo.USER_ID_COLUMN_NAME
        } = :userId AND ${
            BloodSugarInfo.ID_COLUMN_NAME
        } > :startId"
    )
    suspend fun deleteBloodSugarRecordsFromId(
        startId: Long,
        userId: Long
    )


    //TODO:: FUNCTION FOR SELECT PAGE FROM BLOOD SUGAR TABLE
    @Transaction
    @Query(
        "SELECT * FROM ${
            BloodSugarInfo.BLOOD_SUGAR_TABLE_NAME
        } WHERE ${
            BloodSugarInfo.USER_ID_COLUMN_NAME
        } == :userId ORDER BY ${
            BloodSugarInfo.ID_COLUMN_NAME
        } DESC LIMIT :pageSize" +
                " OFFSET ((:page - 1) * :pageSize)"
    )
    suspend fun selectPageBloodSugar(
        pageSize: Int,
        page: Int,
        userId: Long
    ): List<BloodSugarEntity>


    //TODO:: FUNCTION FOR PROVIDE BLOOD SUGAR RECORD COUNT
    @Query(
        "SELECT COUNT(*) FROM ${
            BloodSugarInfo.BLOOD_SUGAR_TABLE_NAME
        } WHERE ${
            BloodSugarInfo.USER_ID_COLUMN_NAME
        } = :userId"
    )
    suspend fun selectBloodSugarCount(
        userId: Long
    ): Long


    //TODO:: FUNCTION FROM DELETE ARTICLES FROM START ID TO END ID
    @Query(
        "DELETE FROM ${
            BloodSugarInfo.BLOOD_SUGAR_TABLE_NAME
        } WHERE( ${
            BloodSugarInfo.ID_COLUMN_NAME
        } > :startId AND ${
            BloodSugarInfo.ID_COLUMN_NAME
        } < :endId) AND ${
            BloodSugarInfo.USER_ID_COLUMN_NAME
        } == :userId"
    )
    suspend fun deleteBloodSugarsFromIdToId(startId: Long, endId: Long, userId: Long)

}//end BloodSugarDao