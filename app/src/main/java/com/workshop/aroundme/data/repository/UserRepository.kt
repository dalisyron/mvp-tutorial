package com.workshop.aroundme.data.repository

import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.local.datasource.UserLocalDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val localDataSource: UserLocalDataSource) {

    fun login(user: UserEntity) {
        localDataSource.login(user)
    }

    fun isLoggedIn() = localDataSource.getUser() != null

}