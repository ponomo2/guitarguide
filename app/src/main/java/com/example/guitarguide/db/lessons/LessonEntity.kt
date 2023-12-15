package com.example.myapplication.db.lessons

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.myapplication.db.files.File
import com.example.myapplication.db.topics.Topic
import com.example.myapplication.db.users.User

@Entity(foreignKeys = [
    ForeignKey(
        entity = Topic::class,
        parentColumns = ["topic_id"],
        childColumns = ["topic"])])
data class Lesson(
    val name: String,
    val topic: Int,
    val theory: String,
    val practice: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lesson_id") val id: Int = 0
)

data class LessonWithFiles(
    @Embedded val lesson: Lesson,
    @Relation(
        parentColumn = "lesson_id",
        entityColumn = "lesson"
    )
    val files: List<File>
)


@Entity(tableName = "users_lessons_saved",
    primaryKeys = ["user_id","lesson_id"],
    foreignKeys = [
        ForeignKey(
            entity = Lesson::class,
            parentColumns = ["lesson_id"],
            childColumns = ["lesson_id"]),
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"])])
data class UserLessonsSavedCrossRef(
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "lesson_id") val lessonId: Int
)

data class UserLessonsSaved(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "lesson_id",
        associateBy = Junction(UserLessonsSavedCrossRef::class)
    )
    val achievements: List<Lesson>
)

@Entity(tableName = "users_lessons_watched",
    primaryKeys = [ "user_id","lesson_id"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"]),
        ForeignKey(
            entity = Lesson::class,
            parentColumns = ["lesson_id"],
            childColumns = ["lesson_id"])])
data class UserLessonsWatchedCrossRef(
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "lesson_id") val lessonId: Int

)

data class UserLessonsWatched(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "lesson_id",
        associateBy = Junction(UserLessonsWatchedCrossRef::class)
    )
    val achievements: List<Lesson>
)
