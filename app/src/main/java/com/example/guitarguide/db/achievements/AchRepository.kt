package com.example.myapplication.db.achievements

import com.example.myapplication.db.files.UserAchievementCrossRef

const val firstUserId = 1

interface AchRepositoryInterface {
    fun getReceivedAchievements(): List<Achievement>?

    suspend fun insertNewAchievement(lessonId: Int, userId: Int)

}

class AchRepository(
    private val achievedDao: AchievedDao
) : AchRepositoryInterface {
    override fun getReceivedAchievements(): List<Achievement>? {
        return achievedDao.getReceivedAchievements()
    }

    override suspend fun insertNewAchievement(achievementId: Int, userId: Int) {
        achievedDao.insert(UserAchievementCrossRef(achievementId, userId))
    }
}