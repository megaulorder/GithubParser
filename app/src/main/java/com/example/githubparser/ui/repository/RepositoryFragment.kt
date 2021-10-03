package com.example.githubparser.ui.repository

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubparser.App
import com.example.githubparser.data.api.ReposSearchResult
import com.example.githubparser.databinding.RepositoryFragmentBinding

class RepositoryFragment : Fragment() {
	private lateinit var binding: RepositoryFragmentBinding
	private lateinit var adapter: RepositoryAdapter
	private lateinit var viewModel: SearchReposViewModel
	private var query: String = "cats"

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = RepositoryFragmentBinding.inflate(inflater, container, false)
		viewModel = ViewModelProvider(
			this,
			ViewModelFactory(App.instance.reposRepository, query))
					.get(SearchReposViewModel::class.java)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupRecyclerView()
		setUpObservers()
	}

	private fun setupRecyclerView() {
		adapter = RepositoryAdapter()

		binding.list.layoutManager = LinearLayoutManager(context)
		binding.list.adapter = adapter
	}

	private fun setUpObservers() {
		viewModel.state
			.distinctUntilChanged()
			.observe(viewLifecycleOwner, Observer { result ->
				when (result) {
					is ReposSearchResult.Success -> {
						Log.d("GithubRepository", "Fragment: display data success")
						showEmptyList(result.data.isNullOrEmpty())
						adapter.setItems(ArrayList(result.data))
					}
					is ReposSearchResult.Error -> {
						Log.d("GithubRepository", "Fragment: error")
						Toast.makeText(
							activity,
							"\uD83D\uDE28 Error ${result.error}}",
							Toast.LENGTH_LONG
						).show()
					}
				}
			})
	}

	private fun showEmptyList(show: Boolean) {
		binding.emptyList.isVisible = show
		binding.list.isVisible = !show
	}
}