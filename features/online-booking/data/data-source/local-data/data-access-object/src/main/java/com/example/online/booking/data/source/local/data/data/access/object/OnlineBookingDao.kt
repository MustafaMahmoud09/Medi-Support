package com.example.online.booking.data.source.local.data.data.access.`object`

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.online.booking.data.source.local.data.entity.execution.onlineBooking.OnlineBookingEntity
import com.example.online.booking.data.source.local.data.entity.execution.onlineBooking.OnlineBookingInfo

@Dao
interface OnlineBookingDao {

    //TODO:: FUNCTION FOR INSERT ONLINE BOOKINGS RECORDS IN DATABASE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnlineBookingRecord(
        bloodPressureRecords: List<OnlineBookingEntity>
    )


    //TODO:: FUNCTION FOR DELETE ONLINE BOOKING FROM DATABASE FROM START ID
    @Query(
        "DELETE FROM ${
            OnlineBookingInfo.ONLINE_BOOKING_TABLE_NAME
        } WHERE ${
            OnlineBookingInfo.USER_ID_COLUMN_NAME
        } = :userId AND ${
            OnlineBookingInfo.ID_COLUMN_NAME
        } > :startId"
    )
    suspend fun deleteOnlineBookingRecordsFromId(
        startId: Long,
        userId: Long
    )


    //TODO:: FUNCTION FOR SELECT PAGE FROM ONLINE BOOKING TABLE
    @Transaction
    @Query(
        "SELECT * FROM ${
            OnlineBookingInfo.ONLINE_BOOKING_TABLE_NAME
        } WHERE ${
            OnlineBookingInfo.USER_ID_COLUMN_NAME
        } == :userId ORDER BY ${
            OnlineBookingInfo.ID_COLUMN_NAME
        } DESC LIMIT :pageSize" +
                " OFFSET ((:page - 1) * :pageSize)"
    )
    suspend fun selectPageOnlineBooking(
        pageSize: Int,
        page: Int,
        userId: Long
    ): List<OnlineBookingEntity>


    //TODO:: FUNCTION FOR PROVIDE ONLINE BOOKING RECORD COUNT
    @Query(
        "SELECT COUNT(*) FROM ${
            OnlineBookingInfo.ONLINE_BOOKING_TABLE_NAME
        } WHERE ${
            OnlineBookingInfo.USER_ID_COLUMN_NAME
        } = :userId"
    )
    suspend fun selectOnlineBookingCount(
        userId: Long
    ): Long


    //TODO:: FUNCTION FROM DELETE ONLINE BOOKING FROM START ID TO END ID
    @Query(
        "DELETE FROM ${
            OnlineBookingInfo.ONLINE_BOOKING_TABLE_NAME
        } WHERE( ${
            OnlineBookingInfo.ID_COLUMN_NAME
        } > :startId AND ${
            OnlineBookingInfo.ID_COLUMN_NAME
        } < :endId) AND ${
            OnlineBookingInfo.USER_ID_COLUMN_NAME
        } == :userId"
    )
    suspend fun deleteOnlineBookingFromIdToId(
        startId: Long,
        endId: Long,
        userId: Long
    )


    //TODO:: FUNCTION FOR DELETE ONLINE BOOKING FROM LOCAL DATA BASE BY ID
    @Query(
        "DELETE FROM ${
            OnlineBookingInfo.ONLINE_BOOKING_TABLE_NAME
        } WHERE ${
            OnlineBookingInfo.ID_COLUMN_NAME
        } = :id"
    )
    suspend fun deleteOnlineBookingById(id: Long)


    @Query(
        "UPDATE ${
            OnlineBookingInfo.ONLINE_BOOKING_TABLE_NAME
        } SET ${
            OnlineBookingInfo.BOOKING_STATUS_COLUMN_NAME
        } = :status WHERE ${
            OnlineBookingInfo.ID_COLUMN_NAME
        } = :id"
    )
    suspend fun updateOnlineBookingStatusById(id: Long, status: Int)

}//end OnlineBookingDao