package com.example.myapplication.db.topics

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.myapplication.db.lessons.Lesson

@Entity
data class Topic(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "topic_id") val id: Int = 0
)

data class TopicWithLessons(
    @Embedded val topic: Topic,
    @Relation(
        parentColumn = "id",
        entityColumn = "topic"
    )
    val lessons: List<Lesson>
)