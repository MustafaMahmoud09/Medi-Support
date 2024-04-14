package com.example.libraries.local.data.shared.entities.entity.execution.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.libraries.core.local.data.entity.declarations.IUserEntity

@Entity(tableName = UserInfo.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey(
        autoGenerate = true
    ) @ColumnInfo(
        name = UserInfo.ID_COLUMN_NAME
    ) override val id: Long = 0,
    @ColumnInfo(
        name = UserInfo.FIRST_NAME_COLUMN_NAME
    ) override val firstName: String?,
    @ColumnInfo(
        name = UserInfo.LAST_NAME_COLUMN_NAME
    ) override val lastName: String?,
    @ColumnInfo(
        name = UserInfo.TOKEN_COLUMN_NAME
    ) override val token: String?,
    @ColumnInfo(
        name = UserInfo.PATH_COLUMN_NAME
    ) override val path: String?,
    @ColumnInfo(
        name = UserInfo.REMEMBER_COLUMN_NAME
    ) override val remember: Boolean,
    @ColumnInfo(
        name = UserInfo.EMAIL_COLUMN_NAME
    ) override val email: String,
    @ColumnInfo(
        name = UserInfo.AUTH_COLUMN_NAME
    ) override val auth: Boolean,
    @ColumnInfo(
        name = UserInfo.COUNT_COLUMN_NAME
    ) override val count: Int,
    @ColumnInfo(
        name = UserInfo.CREATE_AT_COLUMN_NAME
    ) override val createdAt: Long = System.currentTimeMillis(),
) : IUserEntity
