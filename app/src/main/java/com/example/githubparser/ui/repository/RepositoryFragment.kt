package com.example.githubparser.ui.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubparser.R
import com.example.githubparser.data.model.RepositoriesList
import com.example.githubparser.databinding.RepositoryFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryFragment : Fragment() {
    private lateinit var binding: RepositoryFragmentBinding
    private lateinit var viewModel: RepositoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepositoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = RepositoryViewModel()

        val textview = binding.name

        viewModel.repositoriesListCall.enqueue(object : Callback<RepositoriesList> {
            override fun onResponse(
                call: Call<RepositoriesList>,
                response: Response<RepositoriesList>
            ) {
                if (response.isSuccessful) {
                    textview.text =
                        response.body()?.items?.joinToString(separator = "\n") { it -> it.name }
                }
            }

            override fun onFailure(call: Call<RepositoriesList>, t: Throwable) {
                t.printStackTrace()
                textview.text = R.string.network_error_message.toString()
            }
        })
    }
}