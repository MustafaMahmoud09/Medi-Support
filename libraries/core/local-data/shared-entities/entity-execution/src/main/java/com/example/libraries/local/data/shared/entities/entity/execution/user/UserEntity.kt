package com.example.libraries.local.data.shared.entities.entity.execution.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.libraries.core.local.data.entity.declarations.IUserEntity

@Entity(tableName = com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey(
        autoGenerate = true
    ) @ColumnInfo(
        name = com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo.ID_COLUMN_NAME
    ) override val id: Long = 0,
    @ColumnInfo(
        name = com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo.EMAIL_COLUMN_NAME
    ) override val email: String,
    @ColumnInfo(
        name = com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo.PASSWORD_COLUMN_NAME
    ) override val password: String,
    @ColumnInfo(
        name = com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo.AUTH_COLUMN_NAME
    ) override val auth: Boolean,
    @ColumnInfo(
        name = com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo.CREATE_AT_COLUMN_NAME
    ) override val createdAt: Long = System.currentTimeMillis(),
) : com.example.libraries.core.local.data.entity.declarations.IUserEntity
