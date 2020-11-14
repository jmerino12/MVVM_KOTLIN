package com.jmb.mvvm_kotlin.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jmb.mvvm_kotlin.R
import com.jmb.mvvm_kotlin.databinding.ActivityImageDetailBinding
import com.jmb.mvvm_kotlin.databinding.ActivityMainBinding

class ImageDetail : AppCompatActivity() {
    private lateinit var binding: ActivityImageDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (intent.extras != null){
            Glide.with(this).load(intent.getStringExtra("imageUrl")).into(binding.photoView)
        }

    }
}