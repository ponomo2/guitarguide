package com.example.myapplication.db.topics

import com.example.myapplication.db.lessons.Lesson
import com.example.myapplication.db.lessons.WatchedDao

interface TopicRepositoryInterface {

    fun getLessonsForTopic(topicId: Int): List<Lesson>?

    fun getAllTopics(): List<Topic>

    fun getWatchedLessons(): List<Int>?
}

class TopicRepository(
    private val topicDao: TopicDao,
    private val watchedDao: WatchedDao
) : TopicRepositoryInterface {
    override fun getLessonsForTopic(topicId: Int): List<Lesson>? = topicDao.getLessonsForTopic(topicId)

    override fun getAllTopics(): List<Topic> = topicDao.getAllTopics()

    override fun getWatchedLessons(): List<Int>? = watchedDao.getWatchedLessons()

}