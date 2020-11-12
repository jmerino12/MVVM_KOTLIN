package com.jmb.mvvm_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmb.mvvm_kotlin.MainAdapter
import com.jmb.mvvm_kotlin.viewmodel.MainViewModel
import com.jmb.mvvm_kotlin.R

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter: MainAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        adapter = MainAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        observeData()

    }

    fun observeData(){
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}