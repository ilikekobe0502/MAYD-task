package com.mayd.homework.model.api

import com.mayd.homework.model.api.model.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("shorten")
    suspend fun getShorten(@Query("url") url: String): Response<ApiBaseItem<Shorten>>
}