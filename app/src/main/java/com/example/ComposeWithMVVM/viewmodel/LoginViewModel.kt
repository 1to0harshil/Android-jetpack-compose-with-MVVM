package com.example.ComposeWithMVVM.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ComposeWithMVVM.repository.Repository
import com.example.ComposeWithMVVM.dataClasses.Login
import com.example.ComposeWithMVVM.dataClasses.LoginReq
import com.example.ComposeWithMVVM.model.ResponseModel
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    private val repository = Repository()

    private val _login = MutableLiveData<ResponseModel<Login>>()
    val login: LiveData<ResponseModel<Login>> = _login

    fun doLogin(loginReq: LoginReq) {
        viewModelScope.launch {
            try {
                val login = repository.Login(loginReq)
                if (login.isSuccessful && login.body()!= null){
                    _login.value = login.body()
                }
            } catch (ignored: Exception) {

            }
        }
    }

}