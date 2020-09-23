package com.achrafamil.zeusapp.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TrackEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}
