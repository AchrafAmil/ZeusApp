package com.achrafamil.zeusapp.common.di

import com.achrafamil.zeusapp.common.core.TrackRepository
import com.achrafamil.zeusapp.common.data.TrackRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface TrackModule {

    @Binds
    @Singleton
    fun bindTrackRepository(impl: TrackRepositoryImpl): TrackRepository
}
