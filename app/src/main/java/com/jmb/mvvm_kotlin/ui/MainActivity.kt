package com.jmb.mvvm_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.jmb.mvvm_kotlin.MainAdapter
import com.jmb.mvvm_kotlin.viewmodel.MainViewModel
import com.jmb.mvvm_kotlin.R

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var shimmer:ShimmerFrameLayout
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        shimmer = findViewById(R.id.shimmer_view_container)
        adapter = MainAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        observeData()

    }

    fun observeData(){
        shimmer.startShimmer()
        viewModel.fetchUserData().observe(this, Observer {
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}