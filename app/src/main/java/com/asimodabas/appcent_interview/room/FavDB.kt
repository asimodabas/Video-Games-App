package com.asimodabas.appcent_interview.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asimodabas.appcent_interview.model.Detail

@Database(entities = [Detail::class], version = 1)
abstract class FavDB : RoomDatabase() {

    abstract fun favDao(): FavDAO
}