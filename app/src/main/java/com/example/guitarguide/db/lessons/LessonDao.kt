package com.example.myapplication.db.lessons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LessonDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lesson: Lesson)

    @Query("SELECT * from lesson WHERE lesson_id = :lessonId")
    fun getLesson(lessonId: Int): Lesson?
}