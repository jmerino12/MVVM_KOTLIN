package com.jmb.mvvm_kotlin.domain.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.jmb.mvvm_kotlin.Usuario

class Repo {

    fun getUserData():LiveData<MutableList<Usuario>>{
        val mutableData = MutableLiveData<MutableList<Usuario>>()
        FirebaseFirestore.getInstance().collection("Usuarios").get().addOnSuccessListener {result->
            val listData = mutableListOf<Usuario>()
            for (document in result){
                val imageUrl = document.getString("imageUrl")
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val usuario = Usuario(imageUrl!!,nombre!!,descripcion!!)
                listData.add(usuario)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}