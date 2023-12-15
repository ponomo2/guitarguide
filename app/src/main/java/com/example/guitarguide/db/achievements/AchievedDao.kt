package com.example.myapplication.db.achievements

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.db.files.UserAchievementCrossRef

@Dao
interface AchievedDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(achievementReceived: UserAchievementCrossRef)

    @Query("SELECT * FROM achievement a " +
            "WHERE EXISTS(SELECT * FROM users_achievements_received u " +
            "WHERE u.achievement_id = a.achievement_id AND u.user_id = :curUser)" +
            "ORDER BY achievement_id ASC")
    fun getReceivedAchievements(curUser: Int = firstUserId): List<Achievement>?
}