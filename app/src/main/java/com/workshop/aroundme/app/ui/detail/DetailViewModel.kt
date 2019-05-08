package com.workshop.aroundme.app.ui.detail

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.data.repository.PlaceRepository

class DetailViewModel(private val placeRepository: PlaceRepository) : ViewModel() {
    fun onActivityCreated(slug : String?) {
        /*
        slug?.let { mySlug ->
            placeRepository.getPlaceDetail(mySlug)
        } ?: run {
            Toast.makeText(requireContext(), "Slug must not be null", Toast.LENGTH_LONG).show()
        }
        */
    }
}