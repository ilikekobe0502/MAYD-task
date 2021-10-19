package com.mayd.homework.model.api

import com.mayd.homework.model.api.model.response.*
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<Contacts>
}