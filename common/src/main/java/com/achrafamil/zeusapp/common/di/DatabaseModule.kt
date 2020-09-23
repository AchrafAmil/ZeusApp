package com.achrafamil.zeusapp.common.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.achrafamil.zeusapp.common.data.db.AppDatabase
import com.achrafamil.zeusapp.common.data.db.TrackDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return databaseBuilder(appContext, AppDatabase::class.java, "zeus.db")
            .build()
    }

    @Provides
    @Reusable
    fun provideNoteDao(database: AppDatabase): TrackDao {
        return database.trackDao()
    }
}
