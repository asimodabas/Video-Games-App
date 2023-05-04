package com.asimodabas.appcent_interview.room

import androidx.room.*
import com.asimodabas.appcent_interview.model.Detail

@Dao
interface FavDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameDetail: Detail)

    @Delete
    fun delete(gameDetail: Detail)

    @Query("SELECT * FROM Detail")
    suspend fun getDetail(): List<Detail>
}