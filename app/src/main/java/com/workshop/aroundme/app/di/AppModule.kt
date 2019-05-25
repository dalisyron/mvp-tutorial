package com.workshop.aroundme.app.di

import android.content.Context
import android.content.SharedPreferences
import com.workshop.aroundme.app.MyApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val myApplication: MyApplication) {
    @Provides
    fun provideContext() : Context {
        return myApplication.applicationContext
    }

    @Provides
    fun providesSharedPrefrences(context: Context) : SharedPreferences {
        return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
    }
}