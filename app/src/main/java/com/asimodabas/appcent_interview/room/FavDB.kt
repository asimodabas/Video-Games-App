package com.asimodabas.appcent_interview.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asimodabas.appcent_interview.model.Detail

@Database(entities = [Detail::class], version = 1)
abstract class FavDB : RoomDatabase() {

    abstract fun myFavGame(): FavDAO

    companion object {
        @Volatile
        private var instance: FavDB? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavDB::class.java,
            "myFavorites"
        ).build()
    }
}