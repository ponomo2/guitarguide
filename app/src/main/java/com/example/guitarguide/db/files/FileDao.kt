package com.example.myapplication.db.files

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FileDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(file: File)

    @Query("SELECT * from file WHERE lesson = :curLesson ORDER BY file_id ASC")
    fun getFileForLesson(curLesson: Int): List<File>?

    @Query("SELECT * from file WHERE file_id = :fileId")
    fun getFileById(fileId: Int): File?
}