package com.example.interk9compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interk9compose.repository.Repository
import com.example.interk9compose.dataClasses.Login
import com.example.interk9compose.dataClasses.LoginReq
import com.example.interk9compose.model.ResponseModel
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