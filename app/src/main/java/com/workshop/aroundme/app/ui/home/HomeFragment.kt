package com.workshop.aroundme.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.detail.DetailFragment
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.PlaceEntity
import java.lang.ref.WeakReference


/* Q : Why store the view as a field,
when we can just pass the view as a constructor parameter just like the other stuff?*/

class HomeFragment : Fragment(), OnHomePlaceItemClickListener, HomeContract.View {

    private var adapter: ModernHomeAdapter? = null

    private val presenter: HomeContract.Presenter by lazy {
        HomePresenter(
            Injector.providePlaceRepository(requireContext()),
            Injector.provideCategoryRepository()
        ).apply {
            view = WeakReference(this@HomeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onActivityCreated()
    }

    override fun showPlaces(places: List<PlaceEntity>) {

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar)
        progressBar?.visibility = View.GONE
        adapter = ModernHomeAdapter(places, this)
        recyclerView?.adapter = adapter

    }

    override fun showCategories(categories: List<ParentCategoryEntity>) {
        adapter?.parentCategories = categories
    }

    override fun onPlaceItemCliced(placeEntity: PlaceEntity) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, DetailFragment.newInstance(placeEntity.slug))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onItemStarred(placeEntity: PlaceEntity) {
        presenter.onItemStarred(placeEntity)
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

}
