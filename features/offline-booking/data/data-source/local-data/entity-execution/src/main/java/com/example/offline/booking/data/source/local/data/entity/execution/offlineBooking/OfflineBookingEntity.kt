package com.example.offline.booking.data.source.local.data.entity.execution.offlineBooking

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo
import com.example.offline.booking.domain.entity.declarations.IOfflineBookingEntity

@Entity(
    tableName = OfflineBookingInfo.OFFLINE_BOOKING_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [UserInfo.ID_COLUMN_NAME],
            childColumns = [OfflineBookingInfo.USER_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class OfflineBookingEntity(
    @PrimaryKey
    @ColumnInfo(
        name = OfflineBookingInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = OfflineBookingInfo.USER_ID_COLUMN_NAME
    ) override val userId: Long,
    @ColumnInfo(
        name = OfflineBookingInfo.DOCTOR_NAME_COLUMN_NAME
    ) override val doctorName: String,
    @ColumnInfo(
        name = OfflineBookingInfo.SPECIALIZATION_COLUMN_NAME
    ) override val specialization: String,
    @ColumnInfo(
        name = OfflineBookingInfo.TIME_COLUMN_NAME
    ) override val time: String,
    @ColumnInfo(
        name = OfflineBookingInfo.DATE_COLUMN_NAME
    ) override val date: String,
    @ColumnInfo(
        name = OfflineBookingInfo.CLINIC_LOCATION_COLUMN_NAME
    )
    override val clinicLocation: String,
    @ColumnInfo(
        name = OfflineBookingInfo.CREATED_AT_COLUMN_NAME
    )
    override val createdAt: String
): IOfflineBookingEntity
