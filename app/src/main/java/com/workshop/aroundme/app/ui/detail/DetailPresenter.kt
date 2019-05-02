package com.workshop.aroundme.app.ui.detail

import androidx.annotation.MainThread
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.repository.PlaceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class DetailPresenter(var placeRepository: PlaceRepository) : DetailContract.Presenter {

    public lateinit var view : WeakReference<DetailContract.View>
    override fun onActivityCreated(slug: String?) {
        slug?.let { mySlug ->
            placeRepository.getPlaceDetail(mySlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { placeDetailEntity : PlaceDetailEntity ->
                    view.get()?.showItemDetails(placeDetailEntity)
                }
        } ?: run {
            view.get()?.invalidSlugToast()
            //Toast.makeText(requireContext(), "Slug must not be null", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDetailReady(placeDetailEntity: PlaceDetailEntity?) {
        view.get()?.showItemDetails(placeDetailEntity)
    }

}