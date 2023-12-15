package com.example.myapplication.db.lessons

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedDao {
    // здесь нужно поправить параметры
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedLesson: UserLessonsSavedCrossRef)

    @Delete
    suspend fun delete(savedLesson: UserLessonsSavedCrossRef)

    @Query(
        "SELECT * FROM lesson l " +
                "WHERE EXISTS(SELECT lesson_id " +
                "FROM users_lessons_saved u WHERE u.user_id = :curUser AND u.lesson_id = l.lesson_id)" +
                "ORDER BY l.lesson_id ASC")
    fun getSavedLessons(curUser: Int = firstUserId): List<Lesson>?

}