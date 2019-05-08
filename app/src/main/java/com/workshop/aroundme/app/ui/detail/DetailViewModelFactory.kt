package com.workshop.aroundme.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.data.repository.PlaceRepository

class DetailViewModelFactory(private val placeRepository: PlaceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(placeRepository) as T
    }

}