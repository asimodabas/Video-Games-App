package com.asimodabas.appcent_interview.repository

import com.asimodabas.appcent_interview.service.GameAPI
import javax.inject.Inject

class DetailRepository
@Inject constructor(
    private val gameAPI: GameAPI
) : BaseRepository() {

    suspend fun getDetail(
        id: Int,
        apiKey: String
    ) = safeApiRequest {
        gameAPI.getGameDetail(id, apiKey)
    }
}