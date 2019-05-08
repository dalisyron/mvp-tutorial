package com.workshop.aroundme.app.ui.detail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.repository.PlaceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val placeRepository: PlaceRepository) : ViewModel() {

    private val _showPlaceDetails = MutableLiveData<PlaceDetailEntity>()
    val showPlaceDetails = _showPlaceDetails

    private val _showInvalidSlugMessage = MutableLiveData<String>()
    val showInvalidSlugMessage = _showInvalidSlugMessage

    fun onActivityCreated(slug : String?) {
        slug?.let { mySlug ->
            placeRepository.getPlaceDetail(mySlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { placeDetail : PlaceDetailEntity ->
                    placeDetail?.let {
                        _showPlaceDetails.postValue(it)
                        /*
                        recyclerView?.adapter = DetailsAdapter(placeDetail)
                        loading?.visibility = View.GONE
                        */
                    }
                }
        } ?: run {
            _showInvalidSlugMessage.postValue("Slug must not be null")
        }
        /*
        slug?.let { mySlug ->
            placeRepository.getPlaceDetail(mySlug)
        } ?: run {
            Toast.makeText(requireContext(), "Slug must not be null", Toast.LENGTH_LONG).show()
        }
        */
    }
}