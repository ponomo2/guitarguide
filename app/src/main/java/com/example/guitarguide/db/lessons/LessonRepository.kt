package com.example.myapplication.db.lessons

import com.example.myapplication.db.files.File
import com.example.myapplication.db.files.FileDao


const val firstUserId = 1

interface LessonRepositoryInterface {

    fun getLesson(lessonId: Int): Lesson?

    suspend fun insertSavedLesson(lessonId: Int, userId: Int)

    suspend fun deleteSavedLesson(lessonId: Int, userId: Int)

    suspend fun insertWatchedLesson(lessonId: Int, userId: Int)

    suspend fun deleteWatchedLesson(lessonId: Int, userId: Int)

    fun getSavedLessons(): List<Lesson>?

    fun getFilesForLesson(lessonId: Int): List<File>?

    fun getFileById(fileId: Int): File?
}

class LessonRepository(
    private val lessonDao: LessonDao,
    private val watchedDao: WatchedDao,
    private val savedDao: SavedDao,
    private val fileDao: FileDao
) : LessonRepositoryInterface {
    override fun getLesson(lessonId: Int): Lesson? {
        return lessonDao.getLesson(lessonId)
    }

    override suspend fun insertSavedLesson(lessonId: Int, userId: Int) {
        savedDao.insert(UserLessonsSavedCrossRef(userId, lessonId))
    }

    override suspend fun deleteSavedLesson(lessonId: Int, userId: Int) {
        savedDao.delete(UserLessonsSavedCrossRef(userId, lessonId))
    }

    override suspend fun insertWatchedLesson(lessonId: Int, userId: Int) {
        watchedDao.insert(UserLessonsWatchedCrossRef(userId, lessonId))
    }

    override suspend fun deleteWatchedLesson(lessonId: Int, userId: Int) {
        watchedDao.delete(UserLessonsWatchedCrossRef(userId, lessonId))
    }
    override fun getSavedLessons(): List<Lesson>? = savedDao.getSavedLessons()

    override fun getFilesForLesson(lessonId: Int): List<File>? {
        return fileDao.getFileForLesson(lessonId)
    }

    override fun getFileById(fileId: Int): File? {
        return fileDao.getFileById(fileId)
    }

}