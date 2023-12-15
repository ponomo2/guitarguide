package com.example.guitarguide.db

import android.content.Context
import androidx.room.Room
import com.example.myapplication.db.MyDatabase
import com.example.myapplication.db.achievements.AchRepository
import com.example.myapplication.db.achievements.AchRepositoryInterface
import com.example.myapplication.db.lessons.LessonRepository
import com.example.myapplication.db.lessons.LessonRepositoryInterface
import com.example.myapplication.db.topics.TopicRepository
import com.example.myapplication.db.topics.TopicRepositoryInterface

interface AppContainer {
    val topicRepositoryInterface: TopicRepositoryInterface

    val lessonRepositoryInterface: LessonRepositoryInterface

    val achRepositoryInterface: AchRepositoryInterface
}

class AppDataContainer(private val context: Context): AppContainer {

    private val database: MyDatabase by lazy<MyDatabase> {
        Room.databaseBuilder(context, MyDatabase::class.java, "database.db")
            .createFromAsset("databases/initial.db")
            .allowMainThreadQueries()
            .build()
    }

    override val topicRepositoryInterface: TopicRepositoryInterface by lazy {
        TopicRepository(database.topicDao(), database.watchedDao())
    }

    override val lessonRepositoryInterface: LessonRepositoryInterface by lazy {
        LessonRepository(
            database.lessonDao(),
            database.watchedDao(),
            database.savedDao(),
            database.fileDao())
    }

    override val achRepositoryInterface: AchRepositoryInterface by lazy {
        AchRepository(database.achievedDao())
    }
}