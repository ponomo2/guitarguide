package com.example.guitarguide.screens.lesson

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guitarguide.downloader.AndroidDownloader
import com.example.myapplication.db.files.File
import com.example.myapplication.db.lessons.Lesson
import com.example.myapplication.db.lessons.LessonRepositoryInterface
import com.example.myapplication.db.lessons.firstUserId
import kotlinx.coroutines.launch

class LessonViewModel(
    private val lessonRepositoryInterface: LessonRepositoryInterface
    ): ViewModel() {
    fun getLesson(lessonId: Int): Lesson {
        return lessonRepositoryInterface.getLesson(lessonId = lessonId) ?:
        Lesson(
            name = "Error",
            id = 0,
            theory = "No such lesson",
            practice = "No such lesson",
            topic = 0)
    }

    fun getSavedLessons(): List<Lesson> {
        return lessonRepositoryInterface.getSavedLessons() ?: emptyList()
    }

    fun isLessonSaved(lessonId: Int): Boolean {
        var saved: Boolean = false
        getSavedLessons().forEach {
            if (lessonId == it.id) {
                saved = true
            }
        }
        return saved
    }

    fun markLessonAsWatched(lessonId: Int, userId: Int = firstUserId) {
        viewModelScope.launch {
            lessonRepositoryInterface.insertWatchedLesson(lessonId, userId)
        }
    }

    fun unMarkLessonAsWatched(lessonId: Int, userId: Int = firstUserId) {
        viewModelScope.launch {
            lessonRepositoryInterface.deleteWatchedLesson(lessonId, userId)
        }
    }

    fun markLessonAsSaved(lessonId: Int, userId: Int = firstUserId) {
        viewModelScope.launch {
            lessonRepositoryInterface.insertSavedLesson(lessonId, userId)
        }
    }

    fun unMarkLessonAsSaved(lessonId: Int, userId: Int = firstUserId) {
        viewModelScope.launch {
            lessonRepositoryInterface.deleteSavedLesson(lessonId, userId)
        }
    }

    fun getFilesForLesson(lessonId: Int): List<File> {
        return lessonRepositoryInterface.getFilesForLesson(lessonId) ?: emptyList()
    }

    fun downloadFile(context: Context, file: File) {
        val downloader = AndroidDownloader(context, file.name)
        downloader.downloadFile(file.link)
    }
}