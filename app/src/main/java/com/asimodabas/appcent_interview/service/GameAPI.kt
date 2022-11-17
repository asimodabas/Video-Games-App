package com.asimodabas.appcent_interview.service

import com.asimodabas.appcent_interview.model.GameDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GameAPI {
    @GET("games")
    suspend fun getData(@Query("key") apiKey: String): GameDTO
}