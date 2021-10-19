package com.mayd.homework.model.repository

import com.mayd.homework.model.api.ApiService
import com.mayd.homework.model.api.model.response.Shorten
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException

class MaydRepository constructor(private val apiService: ApiService, private val dbRepository: MaydDbRepository) {
    /**
     * get shorten url from api
     *
     * @param url the url you want to make shorten url
     */
    suspend fun getShorten(url:String): Flow<Shorten> {
        return flowOf(apiService.getShorten(url))
                .map { result ->
                    if (!result.isSuccessful) throw HttpException(result)
                    val data = result.body()?.result
                    dbRepository.insertShortenData(data!!)
                    return@map data
                }.flowOn(Dispatchers.IO)
    }

    /**
     * get shorten history from db
     */
    fun getShortenHistory(): Flow<List<Shorten>> {
        return dbRepository.fetchAllHistory()
    }
}