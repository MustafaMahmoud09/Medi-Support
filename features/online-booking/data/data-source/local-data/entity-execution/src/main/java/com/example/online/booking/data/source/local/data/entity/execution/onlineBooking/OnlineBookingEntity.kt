package com.example.online.booking.data.source.local.data.entity.execution.onlineBooking

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo
import com.example.online.booking.domain.entity.declarations.IOnlineBookingEntity

@Entity(
    tableName = OnlineBookingInfo.ONLINE_BOOKING_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [UserInfo.ID_COLUMN_NAME],
            childColumns = [OnlineBookingInfo.USER_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class OnlineBookingEntity(
    @PrimaryKey
    @ColumnInfo(
        name = OnlineBookingInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = OnlineBookingInfo.USER_ID_COLUMN_NAME
    ) override val userId: Long,
    @ColumnInfo(
        name = OnlineBookingInfo.DOCTOR_NAME_COLUMN_NAME
    ) override val doctorName: String,
    @ColumnInfo(
        name = OnlineBookingInfo.SPECIALIZATION_COLUMN_NAME
    ) override val specialization: String,
    @ColumnInfo(
        name = OnlineBookingInfo.BOOKING_STATUS_COLUMN_NAME
    )
    override val bookingStatus: Int,
    @ColumnInfo(
        name = OnlineBookingInfo.DOCTOR_STATUS_COLUMN_NAME
    )
    override val doctorActiveStatus: Int,
    @ColumnInfo(
        name = OnlineBookingInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: String
): IOnlineBookingEntity
