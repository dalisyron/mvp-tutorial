package com.workshop.aroundme.app.ui.detail

import com.workshop.aroundme.data.model.PlaceDetailEntity

interface DetailContract {
    interface View : DetailContract {
        fun invalidSlugToast()
        fun showItemDetails(placeDetailEntity: PlaceDetailEntity?)
    }
    interface Presenter : DetailContract {
        fun onActivityCreated(slug: String?)
        fun onDetailReady(placeDetailEntity: PlaceDetailEntity?)
    }
}