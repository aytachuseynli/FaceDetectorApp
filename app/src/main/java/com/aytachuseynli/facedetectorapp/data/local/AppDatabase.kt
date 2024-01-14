package com.aytachuseynli.facedetectorapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testResultDao(): TestResultDao
}