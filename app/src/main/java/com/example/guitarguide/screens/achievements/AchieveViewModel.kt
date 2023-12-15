package com.example.guitarguide.screens.achievements

import androidx.lifecycle.ViewModel
import com.example.myapplication.db.achievements.AchRepositoryInterface
import com.example.myapplication.db.achievements.Achievement

class AchieveViewModel(private val achRepositoryInterface: AchRepositoryInterface): ViewModel() {
    fun getReceivedAchievements(): List<Achievement>? {
        return achRepositoryInterface.getReceivedAchievements()
    }
}