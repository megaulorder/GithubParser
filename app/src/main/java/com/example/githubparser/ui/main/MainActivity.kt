package com.example.githubparser.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.githubparser.R
import com.example.githubparser.data.model.Repository
import com.example.githubparser.databinding.ActivityMainBinding
import com.example.githubparser.ui.main.viewmodel.RepositoryViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RepositoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = Repository("placeholder", 0)

        viewModel = RepositoryViewModel(repository)

        binding.viewModel = viewModel
    }
}