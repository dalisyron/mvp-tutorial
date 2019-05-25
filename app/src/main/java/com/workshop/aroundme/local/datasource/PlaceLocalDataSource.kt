package com.workshop.aroundme.local.datasource

import com.workshop.aroundme.local.dao.PlaceDao
import com.workshop.aroundme.local.model.LocalPlace
import javax.inject.Inject

class PlaceLocalDataSource @Inject constructor(private val placeDao: PlaceDao) {

    fun getStarredPlaces(): List<LocalPlace> {
        return placeDao.listStarredPlaces()
    }

    fun starPlace(localPlace: LocalPlace) {
        placeDao.insert(localPlace)
    }
}