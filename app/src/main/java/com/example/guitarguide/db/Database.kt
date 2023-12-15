package com.example.myapplication.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.db.achievements.AchievedDao
import com.example.myapplication.db.achievements.Achievement
import com.example.myapplication.db.achievements.AchievementDao
import com.example.myapplication.db.files.File
import com.example.myapplication.db.files.FileDao
import com.example.myapplication.db.files.UserAchievementCrossRef
import com.example.myapplication.db.lessons.Lesson
import com.example.myapplication.db.lessons.LessonDao
import com.example.myapplication.db.lessons.SavedDao
import com.example.myapplication.db.lessons.UserLessonsSavedCrossRef
import com.example.myapplication.db.lessons.UserLessonsWatchedCrossRef
import com.example.myapplication.db.lessons.WatchedDao
import com.example.myapplication.db.topics.Topic
import com.example.myapplication.db.topics.TopicDao
import com.example.myapplication.db.users.User
import com.example.myapplication.db.users.UserDao

@Database(
    entities = [
        User::class,
        Topic::class,
        Lesson::class,
        File::class,
        Achievement::class,
        UserLessonsSavedCrossRef::class,
        UserLessonsWatchedCrossRef::class,
        UserAchievementCrossRef::class],
    version = 1,
    exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun lessonDao(): LessonDao
    abstract fun topicDao(): TopicDao
    abstract fun fileDao(): FileDao
    abstract fun achievementDao(): AchievementDao
    abstract fun savedDao(): SavedDao
    abstract fun watchedDao(): WatchedDao
    abstract fun achievedDao(): AchievedDao

    companion object {
        @Volatile
        private var Instance: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MyDatabase::class.java, "item_database")
                    .createFromAsset("databases/initial.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
