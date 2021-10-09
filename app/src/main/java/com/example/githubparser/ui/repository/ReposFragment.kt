package com.example.githubparser.ui.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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

		setupRecyclerView()
		bindVM()
	}

	private fun setupRecyclerView() {
		adapter = ReposAdapter()

		binding.list.layoutManager = LinearLayoutManager(context)
		binding.list.adapter = adapter
	}

	fun bindVM() {
		with(viewModel) {
			viewLifecycleOwner.lifecycleScope.launchWhenCreated {
				reposFlow.collectLatest { pagingData ->
					adapter.submitData(pagingData)
				}
			}
		}
	}

	private fun showEmptyList(show: Boolean) {
		binding.emptyList.isVisible = show
		binding.list.isVisible = !show
	}
}
