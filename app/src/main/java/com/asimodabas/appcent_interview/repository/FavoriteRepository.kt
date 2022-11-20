package com.asimodabas.appcent_interview.repository

import com.asimodabas.appcent_interview.model.Detail
import com.asimodabas.appcent_interview.room.FavDAO
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao: FavDAO
) {
    suspend fun getDetails() = dao.getDetail()

    fun insertDetail(detail: Detail) = dao.insert(detail)

    fun deleteDetail(detail: Detail) = dao.delete(detail)
}