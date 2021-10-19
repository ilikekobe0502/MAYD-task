package com.mayd.homework.model.repository

import com.mayd.homework.model.api.model.response.Shorten
import com.mayd.homework.model.db.dao.ShortenDao
import javax.inject.Inject

class MaydDbRepository @Inject constructor(private val ShortenDao: ShortenDao) {
    fun fetchAllHistory() = ShortenDao.getAll()
    fun insertShortenData(shorten: Shorten) = ShortenDao.insertItem(shorten)
}
