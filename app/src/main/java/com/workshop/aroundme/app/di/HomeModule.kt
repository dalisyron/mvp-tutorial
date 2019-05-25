package com.workshop.aroundme.app.di

import com.workshop.aroundme.app.ui.home.HomeViewModel
import com.workshop.aroundme.app.ui.home.HomeViewModelFactory
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    /*
    @Provides
    fun providesHomeViewModelFactory(
        placeRepository: PlaceRepository,
        categoryRepository: CategoryRepository
    )
            : HomeViewModelFactory {
        return HomeViewModelFactory(placeRepository, categoryRepository)
    }
    */
    @Provides
    fun providesHomeViewModel(
        placeRepository: PlaceRepository,
        categoryRepository: CategoryRepository
    ): HomeViewModel {
        return HomeViewModel(placeRepository, categoryRepository)
    }
}