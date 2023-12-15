package com.example.myapplication.db.lessons

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WatchedDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(watchedLesson: UserLessonsWatchedCrossRef)

    @Delete
    suspend fun delete(watchedLesson: UserLessonsWatchedCrossRef)

    @Query("SELECT lesson_id from users_lessons_watched  ORDER BY lesson_id ASC")
    fun getWatchedLessons(): List<Int>?
}