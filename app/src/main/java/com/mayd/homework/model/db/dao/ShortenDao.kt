package com.mayd.homework.model.db.dao

import androidx.room.*
import com.mayd.homework.model.api.model.response.Shorten
import kotlinx.coroutines.flow.Flow

@Dao
interface ShortenDao {
    @Query("SELECT * FROM 'history'")
    fun getAll(): Flow<List<Shorten>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(shorten: Shorten)

    @Delete
    fun deleteItem(shorten: Shorten)
}