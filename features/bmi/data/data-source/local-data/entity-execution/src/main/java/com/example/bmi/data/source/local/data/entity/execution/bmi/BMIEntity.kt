package com.example.blood.sugar.data.source.local.data.entity.execution.bloodSugar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.blood.sugar.domain.entity.declarations.IBloodSugarEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

@Entity(
    tableName = BloodSugarInfo.BLOOD_SUGAR_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [UserInfo.ID_COLUMN_NAME],
            childColumns = [BloodSugarInfo.USER_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class BloodSugarEntity(
    @PrimaryKey
    @ColumnInfo(
        name = BloodSugarInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = BloodSugarInfo.USER_ID_COLUMN_NAME
    ) override val userId: Long,
    @ColumnInfo(
        name = BloodSugarInfo.LEVEL_COLUMN_NAME
    ) override val level: Double,
    @ColumnInfo(
        name = BloodSugarInfo.ADVICE_COLUMN_NAME
    ) override val advice: String? = null,
    @ColumnInfo(
        name = BloodSugarInfo.TYPE_COLUMN_NAME
    )
    override val type: String,
    @ColumnInfo(
        name = BloodSugarInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: String
) : IBloodSugarEntity
