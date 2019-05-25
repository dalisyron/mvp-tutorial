package com.workshop.aroundme.app.di

import com.workshop.aroundme.app.MainActivity
import com.workshop.aroundme.app.ui.detail.DetailFragment
import com.workshop.aroundme.app.ui.home.HomeFragment
import com.workshop.aroundme.app.ui.login.LoginFragment
import com.workshop.aroundme.app.ui.starred.StarredFragment
import dagger.Component

@Component(modules = [AppModule::class, HomeModule::class, DatabaseModule::class, DetailModule::class, LoginModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(detailFragment: DetailFragment)
    fun inject(starredFragment: StarredFragment)
    fun inject(loginFragment: LoginFragment)
}