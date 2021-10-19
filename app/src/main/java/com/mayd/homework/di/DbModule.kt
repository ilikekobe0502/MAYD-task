package com.mayd.homework.di

import android.content.Context
import androidx.room.Room
import com.mayd.homework.model.db.HistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DBModule {

    @Singleton
    @Provides
    fun provideContactsDatabase(@ApplicationContext appContext: Context): HistoryDatabase {
        return Room.databaseBuilder(
                appContext,
                HistoryDatabase::class.java,
                HistoryDatabase::class.java.simpleName
        ).build()
    }

    @Singleton
    @Provides
    fun provideContactsDao(db: HistoryDatabase) = db.historyDao()
}

