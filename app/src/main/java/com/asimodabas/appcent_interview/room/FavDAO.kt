package com.asimodabas.appcent_interview.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.asimodabas.appcent_interview.model.Detail

@Dao
interface FavDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameDetail: Detail)

    @Delete
    fun delete(gameDetail: Detail)
}