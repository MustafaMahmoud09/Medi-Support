package com.example.blood.pressure.data.source.local.data.entity.execution.bloodPressure

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.blood.pressure.domain.entity.declarations.IBloodPressureEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

@Entity(
    tableName = BloodPressureInfo.BLOOD_PRESSURE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [UserInfo.ID_COLUMN_NAME],
            childColumns = [BloodPressureInfo.USER_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class BloodPressureEntity(
    @PrimaryKey
    @ColumnInfo(
        name = BloodPressureInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = BloodPressureInfo.USER_ID_COLUMN_NAME
    ) override val userId: Long,
    @ColumnInfo(
        name = BloodPressureInfo.SYSTOLIC_COLUMN_NAME
    ) override val systolic: Long,
    @ColumnInfo(
        name = BloodPressureInfo.DIASTOLIC_COLUMN_NAME
    ) override val diastolic: Long,
    @ColumnInfo(
        name = BloodPressureInfo.ADVICE_COLUMN_NAME
    ) override val advice: String? = null,
    @ColumnInfo(
        name = BloodPressureInfo.TYPE_COLUMN_NAME
    )
    override val type: String,
    @ColumnInfo(
        name = BloodPressureInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: String
) : IBloodPressureEntity
