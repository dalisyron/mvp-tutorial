package com.workshop.aroundme.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val placeRepository: PlaceRepository,
                    private val categoryRepository: CategoryRepository) : ViewModel() {

    private val _showPlaces = MutableLiveData<List<PlaceEntity>>()
    var showPlaces : LiveData<List<PlaceEntity>> = _showPlaces

    private val _showCategories = MutableLiveData<List<ParentCategoryEntity>>()
    var showCategories : LiveData<List<ParentCategoryEntity>> = _showCategories

    fun onActivityCreated() {
        placeRepository.getFeaturedPlaces().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {places : List<PlaceEntity> ->
                _showPlaces.postValue(places)
            }
            .observeOn(Schedulers.io())
            .flatMap {
                categoryRepository.getCategories()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ categories : List<ParentCategoryEntity> ->
                _showCategories.postValue(categories)
            }, {

            })
    }

    fun onItemStarred(placeEntity: PlaceEntity) {
        placeRepository.starPlace(placeEntity)
    }
}