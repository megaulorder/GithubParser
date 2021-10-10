package com.example.githubparser.ui.repos

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.githubparser.data.model.Repo

class ReposAdapter : PagingDataAdapter<Repo, RepoViewHolder>(REPO_COMPARATOR) {

	var repoItemListener: RepoItemListener? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
		return RepoViewHolder.create(parent, repoItemListener)
	}

	override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
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
		fun onRepoClicked()
	}
}
