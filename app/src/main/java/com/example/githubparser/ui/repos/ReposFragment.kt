package com.example.githubparser.ui.repos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.githubparser.App
import com.example.githubparser.R
import com.example.githubparser.data.model.Repo
import com.example.githubparser.databinding.ItemRepoBinding
import com.example.githubparser.databinding.ReposFragmentBinding
import kotlinx.coroutines.flow.collectLatest

class ReposFragment : Fragment(), ReposAdapter.RepoItemListener {

	private lateinit var binding: ReposFragmentBinding
	private lateinit var adapter: ReposAdapter
	private lateinit var viewModel: ReposViewModel
	private var query: String = "cats"

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = ReposFragmentBinding.inflate(inflater, container, false)
		viewModel = ViewModelProvider(
			this,
			ViewModelFactory(App.instance.reposRepository, query)
		)
			.get(ReposViewModel::class.java)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindViewModel()
	}

	private fun bindViewModel() {
		adapter = ReposAdapter(this)

		with(binding) {
			with(adapter) {

				list.adapter = withLoadStateHeaderAndFooter(
					header = ReposLoadStateAdapter(this),
					footer = ReposLoadStateAdapter(this)
				)

				refreshLayout.setOnRefreshListener { refresh() }

				retryButton.setOnClickListener { adapter.retry() }

				with(viewModel) {
					viewLifecycleOwner.lifecycleScope.launchWhenCreated {
						reposFlow.collectLatest { pagingData ->
							submitData(pagingData)
						}
					}

					viewLifecycleOwner.lifecycleScope.launchWhenCreated {
						loadStateFlow.collectLatest { loadState ->
							refreshLayout.isRefreshing = loadState.refresh is LoadState.Loading

							val isListEmpty =
								loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
							emptyList.isVisible = isListEmpty
							list.isVisible = !isListEmpty

							val isNetworkError =
								loadState.refresh is LoadState.Error && adapter.itemCount == 0
							errorMessage.isVisible = isNetworkError
							retryButton.isVisible = isNetworkError
							list.isVisible = !isNetworkError

							Log.d(
								"GithubRepository",
								"ReposFragment: Load state: ${loadState.refresh} list visible: ${list.isVisible}"
							)
						}
					}
				}
			}
		}
	}

	override fun onRepoClicked(binding: ItemRepoBinding, repo: Repo) {
		val description = repo.description?.let { repo.description } ?: ""

		val bundle = bundleOf(
			NAME_KEY to repo.fullName,
			DESCRIPTION_KEY to description,
			AVATAR_URL_KEY to repo.owner.avatarUrl
		)

		Log.d("GithubRepository", "Repos fragment: repo clicked")

		findNavController().navigate(
			R.id.action_repos_fragment_to_repo_detail_fragment, bundle
		)
	}

	companion object {
		const val NAME_KEY = "name"
		const val DESCRIPTION_KEY = "description"
		const val AVATAR_URL_KEY = "avatarUrl"
	}
}
