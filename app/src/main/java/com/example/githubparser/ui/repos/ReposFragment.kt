package com.example.githubparser.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.githubparser.App
import com.example.githubparser.databinding.ReposFragmentBinding
import kotlinx.coroutines.flow.collectLatest

class ReposFragment : Fragment() {
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
		adapter = ReposAdapter()

		with(binding) {
			with(adapter) {

				list.adapter = withLoadStateHeaderAndFooter(
					header = ReposLoadStateAdapter(this),
					footer = ReposLoadStateAdapter(this)
				)

				refreshLayout.setOnRefreshListener { refresh() }

				with(viewModel) {
					viewLifecycleOwner.lifecycleScope.launchWhenCreated {
						reposFlow.collectLatest { pagingData ->
//							val isListEmpty = pagingData.refresh is LoadState.NotLoading && adapter.itemCount == 0
//							emptyList.isVisible = isListEmpty
//							list.isVisible = !isListEmpty

							submitData(pagingData)
						}
					}

					viewLifecycleOwner.lifecycleScope.launchWhenCreated {
						loadStateFlow.collectLatest {
							refreshLayout.isRefreshing = it.refresh is LoadState.Loading
						}
					}
				}
			}
		}
	}
}
