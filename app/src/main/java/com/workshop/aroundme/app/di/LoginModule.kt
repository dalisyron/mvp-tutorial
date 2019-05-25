package com.workshop.aroundme.app.di

import com.workshop.aroundme.app.ui.login.LoginViewModel
import com.workshop.aroundme.data.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun providesLoginViewModel(userRepository : UserRepository) : LoginViewModel {
        return LoginViewModel(userRepository)
    }
}