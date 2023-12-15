package com.example.myapplication.db.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id") val id: Int = 0
)
