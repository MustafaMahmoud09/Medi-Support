package com.example.localdata.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.shared.entity.implementation.user.UserEntity

@Dao
interface UserDao {

    //TODO:: Function For Insert User
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

}//end UserDao