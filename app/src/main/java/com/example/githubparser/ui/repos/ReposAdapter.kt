package com.example.githubparser.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.githubparser.data.model.Repo
import com.example.githubparser.databinding.ItemRepoBinding

class ReposAdapter(private val listener: RepoItemListener) :
	PagingDataAdapter<Repo, ReposViewHolder>(REPO_COMPARATOR) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		ReposViewHolder(
			ItemRepoBinding
				.inflate(
					LayoutInflater.from(parent.context), parent, false
				), listener
		)

	override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
		val repoItem = getItem(position)
		if (repoItem != null) {
			holder.showRepoData(repoItem)
		}
	}

	companion object {
		private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
			override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
				oldItem.fullName == newItem.fullName

			override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
				oldItem == newItem
		}
	}

	interface RepoItemListener {
		fun onRepoClicked(binding: ItemRepoBinding, repo: Repo)
	}
}
