package com.workshop.aroundme.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository


class HomeViewModelFactory(private val placeRepository: PlaceRepository,
                           private val categoryRepository: CategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(placeRepository, categoryRepository) as T
    }

}