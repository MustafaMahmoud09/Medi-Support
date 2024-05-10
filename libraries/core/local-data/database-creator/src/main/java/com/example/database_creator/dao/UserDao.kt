package com.example.database_creator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserInfo
import kotlinx.coroutines.flow.Flow

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


    //TODO:: FUNCTION FOR SELECT USER BY EMAIL
    @Query(
        "SELECT * FROM ${
            UserInfo.USER_TABLE_NAME
        } WHERE ${
            UserInfo.TOKEN_COLUMN_NAME
        }= :accessToken " +
                "LIMIT 1"
    )
    fun getUserByAccessToken(accessToken: String): Flow<List<UserEntity>>


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


    //TODO:: FUNCTION FOR GET USER AUTH NOW
    @Query(
        "SELECT * FROM ${
            UserInfo.USER_TABLE_NAME
        } WHERE (${
            UserInfo.AUTH_COLUMN_NAME
        } = :auth AND ${
            UserInfo.REMEMBER_COLUMN_NAME
        } = :rememberMe) OR ${
            UserInfo.COUNT_COLUMN_NAME
        } = 0 " +
                "LIMIT 1"
    )
    suspend fun getAuthUser(
        auth: Boolean = true,
        rememberMe: Boolean
    ): List<UserEntity>


    //TODO:: FUNCTION FOR UPDATE USER AUTH COUNT
    @Query(
        "UPDATE ${
            UserInfo.USER_TABLE_NAME
        } SET ${
            UserInfo.COUNT_COLUMN_NAME
        } = 1"
    )
    suspend fun updateUsersAuthCount()


    //TODO:: FUNCTION FOR PROVIDE THE USER HAVE NOW ACCESS TOKEN
    @Query(
        "SELECT * FROM ${
            UserInfo.USER_TABLE_NAME
        } WHERE ${
            UserInfo.TOKEN_COLUMN_NAME
        } = :token " +
                "LIMIT 1"
    )
    suspend fun selectUserByAccessToken(token: String): UserEntity


    //TODO:: FUNCTION FOR UPDATE USER DATA TO DEFAULT
    @Query(
        "UPDATE ${
            UserInfo.USER_TABLE_NAME
        } SET ${
            UserInfo.LAST_NAME_COLUMN_NAME
        } = :lastName, ${
            UserInfo.FIRST_NAME_COLUMN_NAME
        } = :firstName, ${
            UserInfo.EMAIL_COLUMN_NAME
        } = :email, ${
            UserInfo.PATH_COLUMN_NAME
        } = :avatar WHERE ${
            UserInfo.TOKEN_COLUMN_NAME
        } = :token"
    )
    suspend fun updateAccountWhereToken(
        email: String,
        lastName: String,
        firstName: String,
        avatar: String,
        token: String
    )


    //TODO:: FUNCTION FOR UPDATE FIRST NAME TO AUTH USER
    @Query(
        "UPDATE ${
            UserInfo.USER_TABLE_NAME
        } SET ${
            UserInfo.FIRST_NAME_COLUMN_NAME
        } = :firstName WHERE ${
            UserInfo.TOKEN_COLUMN_NAME
        } = :token"
    )
    suspend fun updateFirstName(
        firstName: String,
        token: String
    )


    //TODO:: FUNCTION FOR UPDATE LAST NAME TO AUTH USER
    @Query(
        "UPDATE ${
            UserInfo.USER_TABLE_NAME
        } SET ${
            UserInfo.LAST_NAME_COLUMN_NAME
        } = :lastName WHERE ${
            UserInfo.TOKEN_COLUMN_NAME
        } = :token"
    )
    suspend fun updateLastName(
        lastName: String,
        token: String
    )


    //TODO:: FUNCTION FOR DELETE USER ACCOUNT FROM LOCAL DATA BASE
    @Query(
        "DELETE FROM ${
            UserInfo.USER_TABLE_NAME
        } WHERE ${
            UserInfo.TOKEN_COLUMN_NAME
        } = :token"
    )
    suspend fun deleteUserAccountByToken(token: String)

}//end UserDao