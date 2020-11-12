package com.jmb.mvvm_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmb.mvvm_kotlin.Usuario
import com.jmb.mvvm_kotlin.domain.data.network.Repo

class MainViewModel:ViewModel() {
    private val repo = Repo()
    fun fetchUserData():LiveData<MutableList<Usuario>>{
        val mutableData = MutableLiveData<MutableList<Usuario>>()
        repo.getUserData().observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}