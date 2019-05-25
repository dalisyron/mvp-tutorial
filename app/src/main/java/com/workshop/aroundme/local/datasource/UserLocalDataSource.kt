package com.workshop.aroundme.local.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import com.workshop.aroundme.data.model.UserEntity
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun login(user: UserEntity) {
        val userData = Gson().toJson(user, UserEntity::class.java)
        sharedPreferences.edit().putString(KEY_USER, userData).commit()
    }

    fun logout() {
        sharedPreferences.edit().remove(KEY_USER).commit()
    }

    fun getUser(): UserEntity? {
        val userData = sharedPreferences.getString(KEY_USER, null)
        return Gson().fromJson(userData, UserEntity::class.java)
    }


    companion object {
        const val KEY_USER = "user"
    }
}