package com.example.myapplication.db.files

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.myapplication.db.achievements.Achievement
import com.example.myapplication.db.lessons.Lesson
import com.example.myapplication.db.users.User

@Entity(foreignKeys = [
    ForeignKey(
        entity = Lesson::class,
        parentColumns = ["lesson_id"],
        childColumns = ["lesson"])])
data class File(
    val name: String,
    val lesson: Int,
    val description: String,
    val link: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "file_id") val id: Int = 0
)

@Entity(tableName = "users_achievements_received",
    primaryKeys = ["user_id","achievement_id"],
    foreignKeys = [
        ForeignKey(
            entity = Achievement::class,
            parentColumns = ["achievement_id"],
            childColumns = ["achievement_id"]),
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"])])
data class UserAchievementCrossRef(
    @ColumnInfo(name = "achievement_id") val achievementId: Int,
    @ColumnInfo(name = "user_id") val userId: Int
)

data class UserWithAchievements(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "achievement_id",
        associateBy = Junction(UserAchievementCrossRef::class)
    )
    val achievements: List<Achievement>
)
