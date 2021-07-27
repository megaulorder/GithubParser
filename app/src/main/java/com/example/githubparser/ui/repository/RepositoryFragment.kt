package com.example.githubparser.ui.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubparser.R
import com.example.githubparser.databinding.RepositoryFragmentBinding
import com.example.githubparser.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment : Fragment(), RepositoryAdapter.RepositoryItemListener {
    private lateinit var binding: RepositoryFragmentBinding
    private lateinit var adapter: RepositoryAdapter
    private val viewModel: RepositoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepositoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setUpObservers()
    }

    private fun setupRecyclerView() {
        adapter = RepositoryAdapter(this)

        binding.repositoriesRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.repositoriesRecyclerview.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.repositories.observe(viewLifecycleOwner, Observer {
            if (it.status == Resource.Status.SUCCESS)
                adapter.setItems(ArrayList(it.data?.items))
            else if (it.status == Resource.Status.ERROR)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onClickedRepository(login: String, repositoryName: String) {
        println("Navigating to $login/$repositoryName")

        findNavController().navigate(
            R.id.action_repository_fragment_to_repository_detail_fragment,
            bundleOf("login" to login, "repositoryName" to repositoryName)
        )
    }
}