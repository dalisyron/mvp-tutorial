package com.workshop.aroundme.app.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.MyApplication
import com.workshop.aroundme.app.ui.login.LoginViewModel
import com.workshop.aroundme.data.model.PlaceDetailEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailFragment : Fragment() {

    private var slug: String? = null
    private var recyclerView: RecyclerView? = null
    private var loading: View? = null


    @Inject
    lateinit var viewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.component.inject(this)
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

        viewModel.apply {
            showPlaceDetails.observe(this@DetailFragment, Observer {
                recyclerView?.adapter = DetailsAdapter(it)
                loading?.visibility = View.GONE
            })
            showInvalidSlugMessage.observe(this@DetailFragment, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
        viewModel.onActivityCreated(slug)
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
