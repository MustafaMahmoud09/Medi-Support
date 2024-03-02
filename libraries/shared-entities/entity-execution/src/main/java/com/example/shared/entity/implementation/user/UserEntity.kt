package com.example.shared.entity.implementation.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shared.entity.interfaces.IUserEntity

@Entity(tableName = UserInfo.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey @ColumnInfo(
        name = UserInfo.ID_COLUMN_NAME
    ) override val id: Long,
    @ColumnInfo(
        name = UserInfo.EMAIL_COLUMN_NAME
    ) override val email: String,
    @ColumnInfo(
        name = UserInfo.PASSWORD_COLUMN_NAME
    ) override val password: String
) : IUserEntity
