package com.example.database_creator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo


@Dao
interface UserDao {

    //TODO:: Function For Insert User
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    //TODO:: FUNCTION FOR SELECT USER BY EMAIL
    @Query(
        "SELECT * FROM ${
            UserInfo.USER_TABLE_NAME
        } WHERE ${
            UserInfo.EMAIL_COLUMN_NAME
        }= :email " +
                "LIMIT 1"
    )
    suspend fun getUserByEmail(email: String): List<UserEntity>

    //TODO:: FUNCTION FOR UPDATE USER DATA TO DEFAULT
    @Query(
        "UPDATE ${
            UserInfo.USER_TABLE_NAME
        } SET ${
            UserInfo.LAST_NAME_COLUMN_NAME
        } = :lastName, ${
            UserInfo.FIRST_NAME_COLUMN_NAME
        } = :firstName, ${
            UserInfo.PATH_COLUMN_NAME
        } = :path, ${
            UserInfo.TOKEN_COLUMN_NAME
        } = :token, ${
            UserInfo.AUTH_COLUMN_NAME
        } = :auth, ${
            UserInfo.COUNT_COLUMN_NAME
        } = :count, ${
            UserInfo.REMEMBER_COLUMN_NAME
        } = :remember WHERE ${
            UserInfo.EMAIL_COLUMN_NAME
        } = :email"
    )
    suspend fun updateUserData(
        email: String,
        lastName: String,
        firstName: String,
        path: String,
        token: String,
        auth: Boolean,
        count: Int,
        remember: Boolean
    )

    //TODO::FUNCTION FOR GET USER AUTH NOW
    @Query(
        "SELECT * FROM ${
            UserInfo.USER_TABLE_NAME
        } WHERE ${
            UserInfo.AUTH_COLUMN_NAME
        } = :auth AND ${
            UserInfo.REMEMBER_COLUMN_NAME
        } = :rememberMe " +
                "LIMIT 1"
    )
    suspend fun getAuthUser(
        auth: Boolean = true,
        rememberMe: Boolean
    ): List<UserEntity>

}//end UserDao