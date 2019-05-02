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

class DetailFragment : Fragment(), DetailContract.View {

    private var slug: String? = null
    private var recyclerView: RecyclerView? = null
    private var loading: View? = null

    private val presenter: DetailContract.Presenter by lazy {
        DetailPresenter(
            this,
            Injector.providePlaceRepository(requireContext())
        )
    }

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
        presenter.onActivityCreated(slug)
        /*
        slug?.let { mySlug ->
            val repository = Injector.providePlaceRepository(requireContext())
            repository.getPlaceDetail(mySlug, ::onDetailReady)
        } ?: run {
            Toast.makeText(requireContext(), "Slug must not be null", Toast.LENGTH_LONG).show()
        }
        */
    }

    private fun onDetailReady(placeDetailEntity: PlaceDetailEntity?) {
        presenter.onDetailReady(placeDetailEntity)
        /*
        activity?.runOnUiThread {
            placeDetailEntity?.let {
                recyclerView?.adapter = DetailsAdapter(placeDetailEntity)
                loading?.visibility = View.GONE
            }
        }
        */
    }

    override fun invalidSlugToast() {
        Toast.makeText(requireContext(), "Slug must not be null", Toast.LENGTH_LONG).show()
    }

    override fun showItemDetails(placeDetailEntity: PlaceDetailEntity?) {
        activity?.runOnUiThread {
            placeDetailEntity?.let {
                recyclerView?.adapter = DetailsAdapter(placeDetailEntity)
                loading?.visibility = View.GONE
            }
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
