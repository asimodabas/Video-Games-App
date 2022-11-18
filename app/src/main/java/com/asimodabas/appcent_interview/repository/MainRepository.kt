package com.asimodabas.appcent_interview.repository

import com.asimodabas.appcent_interview.service.GameAPI
import javax.inject.Inject

class MainRepository
@Inject constructor(
    private val apiFactory: GameAPI
) : BaseRepository() {

    suspend fun getData(
        apiKey: String
    ) = safeApiRequest {
        apiFactory.getData(apiKey)
    }
}