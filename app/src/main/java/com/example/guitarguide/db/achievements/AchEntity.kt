package com.example.myapplication.db.achievements

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.myapplication.db.users.User

@Entity
data class Achievement(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "achievement_id") val id: Int = 0,
    val name: String,
    val description: String,
    val picture: String,

)

