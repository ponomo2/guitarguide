package com.example.guitarguide.screens.topic

import androidx.lifecycle.ViewModel
import com.example.myapplication.db.lessons.Lesson
import com.example.myapplication.db.topics.Topic
import com.example.myapplication.db.topics.TopicRepositoryInterface

class TopicViewModel(
    private val topicRepositoryInterface: TopicRepositoryInterface): ViewModel() {
    fun getAllTopics(): List<Topic> = topicRepositoryInterface.getAllTopics()

    fun getLessonsForTopic(topicId: Int): List<Lesson>?
        = topicRepositoryInterface.getLessonsForTopic(topicId = topicId)

    fun getWatchedLessons(): List<Int> {
        return topicRepositoryInterface.getWatchedLessons() ?: emptyList()
    }
}