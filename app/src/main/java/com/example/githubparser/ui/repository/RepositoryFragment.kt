package com.example.githubparser.ui.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubparser.R
import com.example.githubparser.databinding.RepositoryFragmentBinding
import com.example.githubparser.ui.MainActivity
import com.example.githubparser.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment : Fragment(), RepositoryAdapter.RepositoryItemListener {
    private lateinit var binding: RepositoryFragmentBinding
    private lateinit var adapter: RepositoryAdapter
    private val viewModel: RepositoryViewModel by viewModels()
    private var query: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepositoryFragmentBinding.inflate(inflater, container, false)

        query = (activity as MainActivity).getSearchQuery().toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (query != "null") {
            viewModel.start(query!!)
            setupRecyclerView()
            setUpObservers()
        } else showPlaceholder(binding.emptyView.text.toString())
    }

    private fun setupRecyclerView() {
        adapter = RepositoryAdapter(this)

        binding.repositoriesRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.repositoriesRecyclerview.adapter = adapter
    }

    private fun setUpObservers() {
        val emptyView = binding.emptyView

        viewModel.repositories?.observe(viewLifecycleOwner, Observer {
            if (it.status == Resource.Status.SUCCESS) {
                if (it.data?.items!!.isNotEmpty()) {
                    emptyView.visibility = View.GONE
                    adapter.setItems(ArrayList(it.data.items))
                } else showPlaceholder(binding.emptyView.text.toString())
            } else if (it.status == Resource.Status.ERROR) {
                showPlaceholder(it.message.toString())
            }
        })
    }

    override fun onClickedRepository(login: String, repositoryName: String) {
        findNavController().navigate(
            R.id.action_repository_fragment_to_repository_detail_fragment,
            bundleOf("login" to login, "repositoryName" to repositoryName)
        )
    }

    private fun showPlaceholder(text: String) {
        val emptyView = binding.emptyView

        binding.repositoriesRecyclerview.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
        emptyView.text = text
    }
}