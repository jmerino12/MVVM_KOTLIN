package com.jmb.mvvm_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.jmb.mvvm_kotlin.MainAdapter
import com.jmb.mvvm_kotlin.viewmodel.MainViewModel
import com.jmb.mvvm_kotlin.R
import com.jmb.mvvm_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),MainAdapter.onImageClickListener {
    private lateinit var adapter: MainAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = MainAdapter(this,this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        observeData()

    }

    fun observeData(){
        binding.shimmerViewContainer.startShimmer()
        viewModel.fetchUserData().observe(this, Observer {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onImageClick(imageUrl: String) {
        val intent = Intent(this, ImageDetail::class.java)
        intent.putExtra("imageUrl",imageUrl)
        startActivity(intent)
    }

    override fun onItemClick(nombre: String) {
        Toast.makeText(this, "El nombre de este item es $nombre", Toast.LENGTH_SHORT).show()
    }
}