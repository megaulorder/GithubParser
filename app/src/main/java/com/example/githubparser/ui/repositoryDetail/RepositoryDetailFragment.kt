package com.example.githubparser.ui.repositoryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.githubparser.data.model.Repository
import com.example.githubparser.databinding.RepositoryDetailFragmentBinding
import com.example.githubparser.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RepositoryDetailFragment : Fragment() {
    private lateinit var binding: RepositoryDetailFragmentBinding
    private val viewModel: RepositoryDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepositoryDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start(arguments?.getString("login")!!, arguments?.getString("repositoryName")!!)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.repository.observe(viewLifecycleOwner, Observer {
            if (it.status == Resource.Status.SUCCESS)
                bindCharacter(it.data!!)
            else if (it.status == Resource.Status.ERROR)
                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun bindCharacter(repository: Repository) {
        binding.name.text = repository.name
        binding.owner.text = repository.owner.login
        binding.description.text = repository.description ?: "—"
        binding.language.text = repository.language ?: "—"
    }
}