package com.example.guitarguide

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guitarguide.screens.achievements.AchieveViewModel
import com.example.guitarguide.screens.lesson.LessonViewModel
import com.example.guitarguide.screens.metronome.MetronomeViewModel
import com.example.guitarguide.screens.topic.TopicViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for LessonViewModel
        initializer {
            LessonViewModel(myApplication().container.lessonRepositoryInterface)
        }

        // Initializer for TopicViewModel
        initializer {
            TopicViewModel(myApplication().container.topicRepositoryInterface)
        }

        // Initializer for AchieveViewModel
        initializer {
            AchieveViewModel(myApplication().container.achRepositoryInterface)
        }

        // Initializer for AchieveViewModel
        initializer {
            MetronomeViewModel()
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.myApplication(): MyApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)

