package com.workshop.aroundme.app.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.data.model.PlaceDetailEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailFragment : Fragment() {

    private var slug: String? = null
    private var recyclerView: RecyclerView? = null
    private var loading: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        slug = arguments?.getString(KEY_SLUG)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(view.context)

        loading = view.findViewById(R.id.loadingBar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        slug?.let { mySlug ->
            val repository = Injector.providePlaceRepository(requireContext())
            repository.getPlaceDetail(mySlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { placeDetail : PlaceDetailEntity ->
                    placeDetail?.let {
                        recyclerView?.adapter = DetailsAdapter(placeDetail)
                        loading?.visibility = View.GONE
                    }
                }
        } ?: run {
            Toast.makeText(requireContext(), "Slug must not be null", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance(slug: String?): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_SLUG, slug)
                }
            }
        }

        private const val KEY_SLUG = "SLUG"
    }
}
