package com.mayd.homework.di

import com.mayd.homework.model.api.ApiService
import com.mayd.homework.model.repository.MaydDbRepository
import com.mayd.homework.model.repository.MaydRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideMaydRepository(apiService: ApiService, dbRepository: MaydDbRepository): MaydRepository {
        return MaydRepository(apiService, dbRepository)
    }
}