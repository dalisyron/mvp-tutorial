package com.workshop.aroundme.app.di

import android.content.Context
import androidx.room.Room
import com.workshop.aroundme.local.AppDatabase
import com.workshop.aroundme.local.dao.PlaceDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun providesAppDatabase(context : Context) : AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "db.data").build()
    }

    @Provides
    fun providesPlaceDao(appDatabase: AppDatabase) : PlaceDao {
        return appDatabase.placeDao()
    }
}