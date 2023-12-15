package com.example.myapplication.db.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

const val firstUserId = 1

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
//
//    @Update
//    suspend fun update(user: User)
//
//    @Delete
//    suspend fun delete(user: User)

    @Query("SELECT * from User WHERE user_id = :id")
    fun getUser(id: Int = firstUserId): Flow<User?>

//    @Query("SELECT * from user ORDER BY name ASC")
//    fun getAllItems(): Flow<List<User>>
}
