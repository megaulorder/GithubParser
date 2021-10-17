package com.example.githubparser.ui.repos

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparser.R
import com.example.githubparser.db.Repo
import com.example.githubparser.databinding.ItemNetworkStateBinding

class ReposLoadStateAdapter<viewHolder : RecyclerView.ViewHolder>(
	private val adapter: PagingDataAdapter<Repo, viewHolder>
) : LoadStateAdapter<ReposLoadStateViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
		ReposLoadStateViewHolder(
			ItemNetworkStateBinding.bind(
				LayoutInflater.from(parent.context)
					.inflate(R.layout.item_network_state, parent, false)
			)
		) { adapter.retry() }

	override fun onBindViewHolder(holder: ReposLoadStateViewHolder, loadState: LoadState) =
		holder.bind(loadState)
}

class ReposLoadStateViewHolder(
	private val binding: ItemNetworkStateBinding,
	private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

	init {
		binding.retryButton.setOnClickListener { retryCallback() }
	}

	fun bind(loadState: LoadState) {
		with(binding) {
			progressBar.isVisible = loadState is LoadState.Loading
			retryButton.isVisible = loadState is LoadState.Error
			errorMessage.isVisible = loadState is LoadState.Error
			if (loadState is LoadState.Error) {
				errorMessage.text = loadState.error.localizedMessage
			}

			Log.d("GithubRepository", "LoadStateAdapter: Load state: $loadState")
		}
	}
}
