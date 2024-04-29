package com.example.bmi.data.source.local.data.entity.execution.bmi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bmi.domain.entity.declarations.IBMIEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo

@Entity(
    tableName = BMIInfo.BMI_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [UserInfo.ID_COLUMN_NAME],
            childColumns = [BMIInfo.USER_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class BMIEntity(
    @PrimaryKey
    @ColumnInfo(
        name = BMIInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = BMIInfo.USER_ID_COLUMN_NAME
    ) override val userId: Long,
    @ColumnInfo(
        name = BMIInfo.RESULT_COLUMN_NAME
    ) override val result: Double,
    @ColumnInfo(
        name = BMIInfo.ADVICE_COLUMN_NAME
    ) override val advice: String? = null,
    @ColumnInfo(
        name = BMIInfo.TYPE_COLUMN_NAME
    )
    override val type: String,
    @ColumnInfo(
        name = BMIInfo.CREATED_AT_COLUMN_NAME
    ) override val createdAt: String
) : IBMIEntity
