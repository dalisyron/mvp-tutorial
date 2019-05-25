package com.workshop.aroundme.app.di

import com.workshop.aroundme.app.ui.detail.DetailViewModel
import com.workshop.aroundme.app.ui.home.HomeViewModel
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository
import dagger.Module
import dagger.Provides

@Module
class DetailModule {
    @Provides
    fun providesDetailViewModel(placeRepository: PlaceRepository) : DetailViewModel {
        return DetailViewModel(placeRepository)
    }
    //no factory
}