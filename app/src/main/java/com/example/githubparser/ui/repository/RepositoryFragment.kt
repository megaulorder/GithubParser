package com.example.githubparser.ui.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubparser.data.model.RepositoriesList
import com.example.githubparser.data.model.Repository
import com.example.githubparser.databinding.RepositoryFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class RepositoryFragment : Fragment() {
    private lateinit var binding: RepositoryFragmentBinding
    private lateinit var adapter: RepositoryAdapter
    private val viewModel: RepositoryViewModel by viewModels()

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepositoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.repositoriesRecyclerview

        viewModel.repositoriesListCall.clone().enqueue(object : Callback<RepositoriesList> {
            override fun onResponse(
                call: Call<RepositoriesList>,
                response: Response<RepositoriesList>
            ) {
                if (response.isSuccessful) {
                    linearLayoutManager =
                        LinearLayoutManager(context)
                    recyclerView.layoutManager = linearLayoutManager

                    adapter = RepositoryAdapter(response.body()?.items as ArrayList<Repository>)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<RepositoriesList>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}