package com.workshop.aroundme.remote.service

import com.google.gson.Gson
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.model.response.FeaturedPlacesResponseDto
import com.workshop.aroundme.remote.model.response.PlaceDetailResponseDto
import javax.inject.Inject

class PlaceService @Inject constructor(private val networkManager: NetworkManager) {

    fun getFeaturedPlacesResponse(): FeaturedPlacesResponseDto {
        val rawData = networkManager.get(URL_FEATURED)
        return Gson().fromJson(rawData, FeaturedPlacesResponseDto::class.java)
    }

    fun getPlaceDetailResponse(slug: String): PlaceDetailResponseDto {
        val url = URL_PLACE_DETAIL + slug
        val rawData = networkManager.get(url)
        return Gson().fromJson(rawData, PlaceDetailResponseDto::class.java)
    }

    companion object {
        const val URL_FEATURED = "http://restapis.xyz/around-me/v1/featured"
        const val URL_PLACE_DETAIL = "http://restapis.xyz/around-me/v1/place/"
    }
}
