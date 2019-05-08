package com.workshop.aroundme.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.data.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _navigateToHomeFragment = MutableLiveData<Unit>()
    val navigateToHomeFragment: LiveData<Unit> = _navigateToHomeFragment

    private val _showMessage = MutableLiveData<Pair<Int, Int>>()
    val showMessage: LiveData<Pair<Int, Int>> = _showMessage

    fun onLoginButtonClicked(userName: String, password: String) {
        if (userName.isNotEmpty() && userName == "reza"
            && password.isNotEmpty() && password == "1234"
        ) {
            val user = UserEntity(userName)
            userRepository.login(user)
            _navigateToHomeFragment.value = Unit
        } else {
            _showMessage.value = R.string.invalid_user_or_pass to R.string.error
        }
    }

}
