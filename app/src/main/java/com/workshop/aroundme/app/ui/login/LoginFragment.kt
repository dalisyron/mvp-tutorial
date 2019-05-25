package com.workshop.aroundme.app.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.MyApplication
import com.workshop.aroundme.app.ui.home.HomeFragment
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            navigateToHomeFragment.observe(this@LoginFragment, Observer {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, HomeFragment())
                    ?.commit()
            })
            showMessage.observe(this@LoginFragment, Observer {
                AlertDialog.Builder(requireContext())
                    .setTitle(it.first)
                    .setMessage(it.second)
                    .setPositiveButton(getString(R.string.ok)) { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    .create()
                    .show()
            })
        }

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        view.findViewById<View>(R.id.login).setOnClickListener {
            viewModel.onLoginButtonClicked(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

}
