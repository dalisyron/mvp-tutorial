package com.workshop.aroundme.app.ui.detail

import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.repository.PlaceRepository

class DetailPresenter(var view : DetailContract.View ,var placeRepository: PlaceRepository) : DetailContract.Presenter {
    override fun onActivityCreated(slug: String?) {
        slug?.let { mySlug ->
            placeRepository.getPlaceDetail(mySlug, ::onDetailReady)
        } ?: run {
            view.invalidSlugToast()
            //Toast.makeText(requireContext(), "Slug must not be null", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDetailReady(placeDetailEntity: PlaceDetailEntity?) {
        view.showItemDetails(placeDetailEntity)
    }

}