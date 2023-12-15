package com.example.myapplication.db.topics

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.db.lessons.Lesson

@Dao
interface TopicDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topic: Topic)

    @Query("SELECT * from topic ORDER BY topic_id ASC")
    fun getAllTopics(): List<Topic>

    @Query("SELECT * from lesson WHERE topic = :topicId ORDER BY lesson_id ASC")
    fun getLessonsForTopic(topicId: Int): List<Lesson>?

}