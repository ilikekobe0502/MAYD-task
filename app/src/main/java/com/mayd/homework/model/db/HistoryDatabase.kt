package com.mayd.homework.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayd.homework.model.api.model.response.Shorten
import com.mayd.homework.model.db.dao.ShortenDao

@Database(entities = [Shorten::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): ShortenDao
}