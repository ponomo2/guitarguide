package com.example.guitarguide.ui.theme


const val HOME = "home"
const val LESSONS = "lessons"
const val ROOT = "root"

sealed class Routes(val route: String) {
    object Menu: Routes(route = "menu")
    object Settings: Routes(route = "settings")
    object LessonsList: Routes(route = "lesson_list")
    object Lesson: Routes(route = "lesson")
    object Achievements: Routes(route = "achievements")
    object Metronome: Routes(route = "metronome")
    object Favorites: Routes(route = "favorites")
}
