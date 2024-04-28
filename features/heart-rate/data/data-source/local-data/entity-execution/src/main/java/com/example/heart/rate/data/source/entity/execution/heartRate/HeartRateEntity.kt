package com.example.heart.rate.data.source.entity.execution.heartRate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.heart.rate.domain.entity.declarations.IHeartRateEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

@Entity(
    tableName = HeartRateInfo.HEART_RATE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [UserInfo.ID_COLUMN_NAME],
            childColumns = [HeartRateInfo.USER_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class HeartRateEntity(
    @PrimaryKey
    @ColumnInfo(
        name = HeartRateInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = HeartRateInfo.USER_ID_COLUMN_NAME
    ) override val userId: Long,
    @ColumnInfo(
        name = HeartRateInfo.RATE_COLUMN_NAME
    ) override val rate: Long,
    @ColumnInfo(
        name = HeartRateInfo.ADVICE_COLUMN_NAME
    ) override val advice: String? = null,
    @ColumnInfo(
        name = HeartRateInfo.TYPE_COLUMN_NAME
    )
    override val type: String,
    @ColumnInfo(
        name = HeartRateInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: String
) : IHeartRateEntity
