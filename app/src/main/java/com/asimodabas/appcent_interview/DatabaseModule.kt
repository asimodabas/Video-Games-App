package com.asimodabas.appcent_interview

import android.content.Context
import androidx.room.Room
import com.asimodabas.appcent_interview.room.FavDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule() {

    @Singleton
    @Provides
    fun provideFavDatabase(@ApplicationContext context: Context): FavDB {
        return Room.databaseBuilder(
            context,
            FavDB::class.java,
            "favdb"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavDao(favDb: FavDB) = favDb.favDao()
}